    public void putAll(final Map<? extends K, ? extends V> map) {
        if (loadFactor <= .5) {
            // The resulting map will be sized for m.size() elements
            ensureCapacity(map.size());
        } else {
            // The resulting map will be tentatively sized for size() +  m.size() elements
            tryCapacity(size() + map.size());
        }
        for (final Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            putObjectValue(entry.getKey(), entry.getValue());
        }
    }
