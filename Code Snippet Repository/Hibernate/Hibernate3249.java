	public static List toList(Object array) {
		if ( array instanceof Object[] ) {
			return Arrays.asList( (Object[]) array ); //faster?
		}
		int size = Array.getLength( array );
		ArrayList list = new ArrayList( size );
		for ( int i = 0; i < size; i++ ) {
			list.add( Array.get( array, i ) );
		}
		return list;
	}
