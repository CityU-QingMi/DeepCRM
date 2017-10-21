    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (Map.Entry<Object, Object> entry : delegate.entrySet()) {
            Map.Entry<K, V> dereferenced = dereferenceEntry(entry);
            if (dereferenced != null) {
                entrySet.add(dereferenced);
            }
        }
        return Collections.unmodifiableSet(entrySet);
    }
