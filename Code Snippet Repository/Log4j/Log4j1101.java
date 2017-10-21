    @Deprecated
    protected Map<String, List<String>> getMap() {
        final Map<String, List<String>> result = new HashMap<>(map.size());
        map.forEach(new BiConsumer<String, List<String>>() {
            @Override
            public void accept(final String key, final List<String> value) {
                result.put(key, value);
            }
        });
        return result;
    }
