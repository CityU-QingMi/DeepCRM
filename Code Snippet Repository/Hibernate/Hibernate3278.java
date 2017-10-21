	@SuppressWarnings("")
	private void readObject(java.io.ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();

		// Initialize each segment to be minimally sized, and let grow.
		for ( int i = 0; i < segments.length; ++i ) {
			segments[i].setTable( new HashEntry[1] );
		}

		// Read the keys and values, and put the mappings in the table
		for (; ; ) {
			K key = (K) s.readObject();
			V value = (V) s.readObject();
			if ( key == null ) {
				break;
			}
			put( key, value );
		}
	}
