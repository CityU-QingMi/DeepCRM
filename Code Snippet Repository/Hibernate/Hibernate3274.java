		private void notifyEvictionListener(Set<HashEntry<K, V>> evicted) {
			// piggyback listener invocation on callers thread outside lock
			if ( evicted != null ) {
				Map<K, V> evictedCopy;
				if ( evicted.size() == 1 ) {
					HashEntry<K, V> evictedEntry = evicted.iterator().next();
					evictedCopy = singletonMap( evictedEntry.key, evictedEntry.value );
				}
				else {
					evictedCopy = new HashMap<K, V>( evicted.size() );
					for ( HashEntry<K, V> he : evicted ) {
						evictedCopy.put( he.key, he.value );
					}
					evictedCopy = unmodifiableMap( evictedCopy );
				}
				evictionListener.onEntryEviction( evictedCopy );
			}
		}
