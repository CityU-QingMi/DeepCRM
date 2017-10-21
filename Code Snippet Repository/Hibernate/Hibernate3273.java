		private Set<HashEntry<K, V>> attemptEviction(boolean lockedAlready) {
			Set<HashEntry<K, V>> evicted = null;
			boolean obtainedLock = lockedAlready || tryLock();
			if ( !obtainedLock && eviction.thresholdExpired() ) {
				lock();
				obtainedLock = true;
			}
			if ( obtainedLock ) {
				try {
					if ( eviction.thresholdExpired() ) {
						evicted = eviction.execute();
					}
				}
				finally {
					if ( !lockedAlready ) {
						unlock();
					}
				}
			}
			return evicted;
		}
