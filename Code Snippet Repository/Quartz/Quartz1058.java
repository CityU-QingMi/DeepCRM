  @Override
  public Map<K, V> getAll(Collection<? extends K> keys) {
    HashSet<String> tempSet = new HashSet<String>();
    for (K key : keys) {
      tempSet.add(serializeToString(key));
    }

    Map<String, V> m = toolkitStore.getAll(tempSet);
    Map<K, V> tempMap = m.isEmpty() ? Collections.EMPTY_MAP : new HashMap<K, V>();

    for (Entry<String, V> entry : m.entrySet()) {
      tempMap.put((K) deserializeFromString(entry.getKey()), entry.getValue());
    }

    return tempMap;
  }
