    @Override
    public void removeAll(final Iterable<String> keys) {
        final StringMap map = localMap.get();
        if (map != null) {
            final StringMap copy = createStringMap(map);
            for (final String key : keys) {
                copy.remove(key);
            }
            copy.freeze();
            localMap.set(copy);
        }
    }
