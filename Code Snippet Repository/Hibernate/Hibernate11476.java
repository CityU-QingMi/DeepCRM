		@Override
		public Map<K, V> toMap() {
			Map<K, V> map = new HashMap<K, V>();
			CloseableIterator<CacheEntry<K, V>> it = entryIterable.iterator();
			try {
				while (it.hasNext()) {
					CacheEntry<K, V> entry = it.next();
					V value = entry.getValue();
					if (value != null) {
						map.put(entry.getKey(), value);
					}
				}
				return map;
			}
			finally {
				it.close();
			}
		}
