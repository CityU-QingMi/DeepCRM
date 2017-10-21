		@Nullable
		private Reference<K, V> findInChain(Reference<K, V> reference, @Nullable Object key, int hash) {
			Reference<K, V> currRef = reference;
			while (currRef != null) {
				if (currRef.getHash() == hash) {
					Entry<K, V> entry = currRef.get();
					if (entry != null) {
						K entryKey = entry.getKey();
						if (entryKey == key || entryKey.equals(key)) {
							return currRef;
						}
					}
				}
				currRef = currRef.getNext();
			}
			return null;
		}
