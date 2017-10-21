    @Override
    public void remove(final String key) {
        final StringMap map = localMap.get();
        if (map != null) {
            final StringMap copy = createStringMap(map);
            copy.remove(key);
            copy.freeze();
            localMap.set(copy);
        }
    }
