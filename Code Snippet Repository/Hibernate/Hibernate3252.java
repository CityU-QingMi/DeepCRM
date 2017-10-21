	public static String toString(Object[] array) {
		StringBuilder sb = new StringBuilder();
		sb.append( "[" );
		for ( int i = 0; i < array.length; i++ ) {
			sb.append( array[i] );
			if ( i < array.length - 1 ) {
				sb.append( "," );
			}
		}
		sb.append( "]" );
		return sb.toString();
	}
