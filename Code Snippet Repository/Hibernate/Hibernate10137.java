	public static String getLastComponent(String s) {
		if ( s == null ) {
			return null;
		}
		final int lastDot = s.lastIndexOf( "." );
		if ( lastDot == -1 ) {
			return s;
		}
		else {
			return s.substring( lastDot + 1 );
		}
	}
