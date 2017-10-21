		boolean containsKey(Object key, int hash) {
			if ( count != 0 ) { // read-volatile
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null ) {
					if ( e.hash == hash && keyEq( key, e.key() ) ) {
						return true;
					}
					e = e.next;
				}
			}
			return false;
		}
