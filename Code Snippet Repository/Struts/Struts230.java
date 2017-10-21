    private <T> ContainerBuilder alias(final Key<T> key,
                                       final Key<T> aliasKey) {
        ensureNotCreated();
        checkKey(aliasKey);

        final InternalFactory<? extends T> scopedFactory = (InternalFactory<? extends T>) factories.get(key);
        if (scopedFactory == null) {
            throw new DependencyException("Dependency mapping for " + key + " doesn't exists.");
        }
        factories.put(aliasKey, scopedFactory);
        return this;
    }
