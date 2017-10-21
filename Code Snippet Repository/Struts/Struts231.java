    private <T> ContainerBuilder constant(final Class<T> type, final String name, final T value) {
        InternalFactory<T> factory = new InternalFactory<T>() {
            public T create(InternalContext ignored) {
                return value;
            }

            @Override
            public String toString() {
                return new LinkedHashMap<String, Object>() {
                    {
                        put("type", type);
                        put("name", name);
                        put("value", value);
                    }
                }.toString();
            }
        };

        return factory(Key.newInstance(type, name), factory, Scope.PROTOTYPE);
    }
