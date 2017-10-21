		V remove(Object key, int hash, Object value) {
			lock();
			try {
				int c = count - 1;
				HashEntry<K, V>[] tab = table;
				int index = hash & tab.length - 1;
				HashEntry<K, V> first = tab[index];
				HashEntry<K, V> e = first;
				while ( e != null && ( e.hash != hash || !key.equals( e.key ) ) ) {
					e = e.next;
				}

				V oldValue = null;
				if ( e != null ) {
					V v = e.value;
					if ( value == null || value.equals( v ) ) {
						oldValue = v;
						// All entries following removed node can stay
						// in list, but all preceding ones need to be
						// cloned.
						++modCount;

						// e was removed
						eviction.onEntryRemove( e );

						HashEntry<K, V> newFirst = e.next;
						for ( HashEntry<K, V> p = first; p != e; p = p.next ) {
							// TODO A remove operation makes the map behave like all the other keys in the bucket were just added???
							// allow p to be GC-ed
							eviction.onEntryRemove( p );
							newFirst = eviction.createNewEntry( p.key, p.hash, newFirst, p.value );
							// and notify eviction algorithm about new hash entries
							eviction.onEntryMiss( newFirst );
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
