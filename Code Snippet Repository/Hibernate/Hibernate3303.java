		V get(Object key, int hash) {
			if ( count != 0 ) { // read-volatile
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null ) {
					if ( e.hash == hash && keyEq( key, e.key() ) ) {
						Object opaque = e.valueRef;
						if ( opaque != null ) {
							return e.dereferenceValue( opaque );
						}

						return readValueUnderLock( e );  // recheck
					}
					e = e.next;
				}
			}
			return null;
		}
