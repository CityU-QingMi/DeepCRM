		V put(K key, int hash, V value, boolean onlyIfAbsent) {
			lock();
			try {
				removeStale();
				int c = count;
				if ( c++ > threshold ) {// ensure capacity
					int reduced = rehash();
					if ( reduced > 0 ) {
						// adjust from possible weak cleanups
						count = ( c -= reduced ) - 1; // write-volatile
					}
				}

				HashEntry<K, V>[] tab = table;
				int index = hash & ( tab.length - 1 );
				HashEntry<K, V> first = tab[index];
				HashEntry<K, V> e = first;
				while ( e != null && ( e.hash != hash || !keyEq( key, e.key() ) ) ) {
					e = e.next;
				}

				V oldValue;
				if ( e != null ) {
					oldValue = e.value();
					if ( !onlyIfAbsent ) {
						e.setValue( value, valueType, refQueue );
					}
				}
				else {
					oldValue = null;
					++modCount;
					tab[index] = newHashEntry( key, hash, first, value );
					count = c; // write-volatile
				}
				return oldValue;
			}
			finally {
				unlock();
			}
		}
