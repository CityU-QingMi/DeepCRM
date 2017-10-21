    public void putAll(final Map<String, String> m) {
        if (!useMap) {
            return;
        }
        Map<String, String> map = localMap.get();
        map = map == null ? new HashMap<String, String>(m.size()) : new HashMap<>(map);
        for (final Map.Entry<String, String> e : m.entrySet()) {
            map.put(e.getKey(), e.getValue());
        }
        localMap.set(Collections.unmodifiableMap(map));
    }
