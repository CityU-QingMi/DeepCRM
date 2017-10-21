		boolean replace(K key, int hash, V oldValue, V newValue) {
			lock();
			Set<HashEntry<K, V>> evicted = null;
			try {
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null && ( e.hash != hash || !key.equals( e.key ) ) ) {
					e = e.next;
				}

				boolean replaced = false;
				if ( e != null && oldValue.equals( e.value ) ) {
					replaced = true;
					e.value = newValue;
					if ( eviction.onEntryHit( e ) ) {
						evicted = attemptEviction( true );
					}
				}
				return replaced;
			}
			finally {
				unlock();
				notifyEvictionListener( evicted );
			}
		}
