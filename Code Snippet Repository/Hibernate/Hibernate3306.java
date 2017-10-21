		boolean replace(K key, int hash, V oldValue, V newValue) {
			lock();
			try {
				removeStale();
				HashEntry<K, V> e = getFirst( hash );
				while ( e != null && ( e.hash != hash || !keyEq( key, e.key() ) ) ) {
					e = e.next;
				}

				boolean replaced = false;
				if ( e != null && oldValue.equals( e.value() ) ) {
					replaced = true;
					e.setValue( newValue, valueType, refQueue );
				}
				return replaced;
			}
			finally {
				unlock();
			}
		}
