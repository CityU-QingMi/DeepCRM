    private <T> ContainerBuilder factory(final Key<T> key,
                                         InternalFactory<? extends T> factory, Scope scope) {
        ensureNotCreated();
        checkKey(key);
        final InternalFactory<? extends T> scopedFactory = scope.scopeFactory(key.getType(), key.getName(), factory);
        factories.put(key, scopedFactory);
        if (scope == Scope.SINGLETON) {
            singletonFactories.add(new InternalFactory<T>() {
                public T create(InternalContext context) {
                    try {
                        context.setExternalContext(ExternalContext.newInstance(null, key, context.getContainerImpl()));
                        return scopedFactory.create(context);
                    } finally {
                        context.setExternalContext(null);
                    }
                }
            });
        }
        return this;
    }
