		void clear() {
			if ( count != 0 ) {
				lock();
				try {
					HashEntry<K, V>[] tab = table;
					for ( int i = 0; i < tab.length; i++ ) {
						tab[i] = null;
					}
					++modCount;
					// replace the reference queue to avoid unnecessary stale cleanups
					refQueue = new ReferenceQueue<Object>();
					count = 0; // write-volatile
				}
				finally {
					unlock();
				}
			}
		}
