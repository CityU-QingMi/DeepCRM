		@Override
		public Set<HashEntry<K, V>> execute() {
			Set<HashEntry<K, V>> evictedCopy = new HashSet<HashEntry<K, V>>();
			for ( HashEntry<K, V> e : accessQueue ) {
				put( e, e.value );
			}
			evictedCopy.addAll( evicted );
			accessQueue.clear();
			evicted.clear();
			return evictedCopy;
		}
