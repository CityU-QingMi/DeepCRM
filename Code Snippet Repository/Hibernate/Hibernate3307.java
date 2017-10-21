		V replace(K key, int hash, V newValue) {
			lock();
			try {
				removeStale();
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null && ( e.hash != hash || !keyEq( key, e.key() ) ) ) {
					e = e.next;
				}

				V oldValue = null;
				if ( e != null ) {
					oldValue = e.value();
					e.setValue( newValue, valueType, refQueue );
				}
				return oldValue;
			}
			finally {
				unlock();
			}
		}
