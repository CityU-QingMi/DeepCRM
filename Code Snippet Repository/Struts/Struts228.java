    public <T> ContainerBuilder factory(final Class<T> type, final String name,
                                        final Factory<? extends T> factory, Scope scope) {
        InternalFactory<T> internalFactory = new InternalFactory<T>() {

            public T create(InternalContext context) {
                try {
                    Context externalContext = context.getExternalContext();
                    return factory.create(externalContext);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String toString() {
                return new LinkedHashMap<String, Object>() {{
                    put("type", type);
                    put("name", name);
                    put("factory", factory);
                }}.toString();
            }
        };

        return factory(Key.newInstance(type, name), internalFactory, scope);
    }
