        Object construct(InternalContext context, Class<? super T> expectedType) {
            ConstructionContext<T> constructionContext = context.getConstructionContext(this);

            // We have a circular reference between constructors. Return a proxy.
            if (constructionContext.isConstructing()) {
                // TODO (crazybob): if we can't proxy this object, can we proxy the
                // other object?
                return constructionContext.createProxy(expectedType);
            }

            // If we're re-entering this factory while injecting fields or methods,
            // return the same instance. This prevents infinite loops.
            T t = constructionContext.getCurrentReference();
            if (t != null) {
                return t;
            }

            try {
                // First time through...
                constructionContext.startConstruction();
                try {
                    Object[] parameters = getParameters(constructor, context, parameterInjectors);
                    t = constructor.newInstance(parameters);
                    constructionContext.setProxyDelegates(t);
                } finally {
                    constructionContext.finishConstruction();
                }

                // Store reference. If an injector re-enters this factory, they'll
                // get the same reference.
                constructionContext.setCurrentReference(t);

                // Inject fields and methods.
                for (Injector injector : injectors) {
                    injector.inject(context, t);
                }

                return t;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            } finally {
                constructionContext.removeCurrentReference();
            }
        }
