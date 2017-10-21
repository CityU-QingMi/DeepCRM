		@Override
		public Set<HashEntry<K, V>> onEntryMiss(HashEntry<K, V> e) {
			put( e, e.value );
			if ( !evicted.isEmpty() ) {
				Set<HashEntry<K, V>> evictedCopy = new HashSet<HashEntry<K, V>>();
				evictedCopy.addAll( evicted );
				evicted.clear();
				return evictedCopy;
			}
			else {
				return Collections.emptySet();
			}
		}
