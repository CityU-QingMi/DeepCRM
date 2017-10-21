		protected boolean removeEldestEntry(Map.Entry<HashEntry<K, V>, V> eldest) {
			boolean aboveThreshold = isAboveThreshold();
			if ( aboveThreshold ) {
				HashEntry<K, V> evictedEntry = eldest.getKey();
				segment.evictionListener.onEntryChosenForEviction( evictedEntry.value );
				segment.remove( evictedEntry.key, evictedEntry.hash, null );
				evicted.add( evictedEntry );
			}
			return aboveThreshold;
		}
