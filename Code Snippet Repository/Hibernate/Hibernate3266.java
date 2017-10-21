		V get(Object key, int hash) {
			int c = count;
			if ( c != 0 ) { // read-volatile
				V result = null;
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null ) {
					if ( e.hash == hash && key.equals( e.key ) ) {
						V v = e.value;
						if ( v != null ) {
							result = v;
							break;
						}
						else {
							result = readValueUnderLock( e ); // recheck
							break;
						}
					}
					e = e.next;
				}
				// a hit
				if ( result != null ) {
					if ( eviction.onEntryHit( e ) ) {
						Set<HashEntry<K, V>> evicted = attemptEviction( false );
						notifyEvictionListener( evicted );
					}
				}
				return result;
			}
			return null;
		}
