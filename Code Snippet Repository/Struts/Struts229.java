    public <T> ContainerBuilder factory(final Class<T> type, final String name,
                                        final Class<? extends T> implementation, final Scope scope) {
        // This factory creates new instances of the given implementation.
        // We have to lazy load the constructor because the Container
        // hasn't been created yet.
        InternalFactory<? extends T> factory = new InternalFactory<T>() {

            volatile ContainerImpl.ConstructorInjector<? extends T> constructor;

            @SuppressWarnings("unchecked")
            public T create(InternalContext context) {
                if (constructor == null) {
                    this.constructor =
                            context.getContainerImpl().getConstructor(implementation);
                }
                return (T) constructor.construct(context, type);
            }

            @Override
            public String toString() {
                return new LinkedHashMap<String, Object>() {{
                    put("type", type);
                    put("name", name);
                    put("implementation", implementation);
                    put("scope", scope);
                }}.toString();
            }
        };

        return factory(Key.newInstance(type, name), factory, scope);
    }
