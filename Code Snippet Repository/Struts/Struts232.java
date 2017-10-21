    public Container create(boolean loadSingletons) {
        ensureNotCreated();
        created = true;
        final ContainerImpl container = new ContainerImpl(new HashMap<>(factories));
        if (loadSingletons) {
            container.callInContext(new ContainerImpl.ContextualCallable<Void>() {
                public Void call(InternalContext context) {
                    for (InternalFactory<?> factory : singletonFactories) {
                        factory.create(context);
                    }
                    return null;
                }
            });
        }
        container.injectStatics(staticInjections);
        return container;
    }
