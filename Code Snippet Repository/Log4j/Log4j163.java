    @Override
    public <V> void forEach(final BiConsumer<String, ? super V> action) {
        final Map<String, String> map = localMap.get();
        if (map == null) {
            return;
        }
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            action.accept(entry.getKey(), (V) entry.getValue());
        }
    }
