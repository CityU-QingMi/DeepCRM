		boolean containsValue(Object value) {
			if ( count != 0 ) { // read-volatile
				HashEntry<K, V>[] tab = table;
				int len = tab.length;
				for ( int i = 0; i < len; i++ ) {
					for ( HashEntry<K, V> e = tab[i]; e != null; e = e.next ) {
						Object opaque = e.valueRef;
						V v;

						if ( opaque == null ) {
							v = readValueUnderLock( e ); // recheck
						}
						else {
							v = e.dereferenceValue( opaque );
						}

						if ( value.equals( v ) ) {
							return true;
						}
					}
				}
			}
			return false;
		}
