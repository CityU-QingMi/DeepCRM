		@Override
		public Set<HashEntry<K, V>> execute() {
			Set<HashEntry<K, V>> evicted = new HashSet<HashEntry<K, V>>();
			try {
				for ( LIRSHashEntry<K, V> e : accessQueue ) {
					if ( e.isResident() ) {
						e.hit( evicted );
					}
				}
				removeFromSegment( evicted );
			}
			finally {
				accessQueue.clear();
			}
			return evicted;
		}
