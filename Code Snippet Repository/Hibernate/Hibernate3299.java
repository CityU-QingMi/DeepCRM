	private void writeObject(java.io.ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();

		for ( int k = 0; k < segments.length; ++k ) {
			Segment<K, V> seg = segments[k];
			seg.lock();
			try {
				HashEntry<K, V>[] tab = seg.table;
				for ( int i = 0; i < tab.length; ++i ) {
					for ( HashEntry<K, V> e = tab[i]; e != null; e = e.next ) {
						K key = e.key();
						if ( key == null ) {
							// Skip GC'd keys
							continue;
						}

						s.writeObject( key );
						s.writeObject( e.value() );
					}
				}
			}
			finally {
				seg.unlock();
			}
		}
		s.writeObject( null );
		s.writeObject( null );
	}
