    public void removeAll(final Iterable<String> keys) {
        final Map<String, String> map = localMap.get();
        if (map != null) {
            final Map<String, String> copy = new HashMap<>(map);
            for (final String key : keys) {
                copy.remove(key);
            }
            localMap.set(Collections.unmodifiableMap(copy));
        }
    }
