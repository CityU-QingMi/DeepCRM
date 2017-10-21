		V remove(Object key, int hash, Object value, boolean refRemove) {
			lock();
			try {
				if ( !refRemove ) {
					removeStale();
				}
				int c = count - 1;
				HashEntry<K, V>[] tab = table;
				int index = hash & ( tab.length - 1 );
				HashEntry<K, V> first = tab[index];
				HashEntry<K, V> e = first;
				// a ref remove operation compares the Reference instance
				while ( e != null && key != e.keyRef
						&& ( refRemove || hash != e.hash || !keyEq( key, e.key() ) ) ) {
					e = e.next;
				}

				V oldValue = null;
				if ( e != null ) {
					V v = e.value();
					if ( value == null || value.equals( v ) ) {
						oldValue = v;
						// All entries following removed node can stay
						// in list, but all preceding ones need to be
						// cloned.
						++modCount;
						HashEntry<K, V> newFirst = e.next;
						for ( HashEntry<K, V> p = first; p != e; p = p.next ) {
							K pKey = p.key();
							if ( pKey == null ) { // Skip GC'd keys
								c--;
								continue;
							}

							newFirst = newHashEntry( pKey, p.hash, newFirst, p.value() );
						}
						tab[index] = newFirst;
						count = c; // write-volatile
					}
				}
				return oldValue;
			}
			finally {
				unlock();
			}
		}
