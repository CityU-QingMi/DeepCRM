		V replace(K key, int hash, V newValue) {
			lock();
			Set<HashEntry<K, V>> evicted = null;
			try {
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null && ( e.hash != hash || !key.equals( e.key ) ) ) {
					e = e.next;
				}

				V oldValue = null;
				if ( e != null ) {
					oldValue = e.value;
					e.value = newValue;
					if ( eviction.onEntryHit( e ) ) {
						evicted = attemptEviction( true );
					}
				}
				return oldValue;
			}
			finally {
				unlock();
				notifyEvictionListener( evicted );
			}
		}
