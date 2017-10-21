	public static String join(String seperator, Iterator objects) {
		StringBuilder buf = new StringBuilder();
		if ( objects.hasNext() ) {
			buf.append( objects.next() );
		}
		while ( objects.hasNext() ) {
			buf.append( seperator ).append( objects.next() );
		}
		return buf.toString();
	}
