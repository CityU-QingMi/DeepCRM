		boolean containsValue(Object value) {
			if ( count != 0 ) { // read-volatile
				HashEntry<K, V>[] tab = table;
				int len = tab.length;
				for ( int i = 0; i < len; i++ ) {
					for ( HashEntry<K, V> e = tab[i]; e != null; e = e.next ) {
						V v = e.value;
						if ( v == null ) {
							v = readValueUnderLock( e );
						}
						if ( value.equals( v ) ) {
							return true;
						}
					}
				}
			}
			return false;
		}
