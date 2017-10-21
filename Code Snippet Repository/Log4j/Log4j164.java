    @Override
    public <V, S> void forEach(final TriConsumer<String, ? super V, S> action, final S state) {
        final Map<String, String> map = localMap.get();
        if (map == null) {
            return;
        }
        for (final Map.Entry<String, String> entry : map.entrySet()) {
            action.accept(entry.getKey(), (V) entry.getValue(), state);
        }
    }
