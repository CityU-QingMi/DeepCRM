	public static String join(String seperator, String[] strings) {
		int length = strings.length;
		if ( length == 0 ) {
			return "";
		}
		// Allocate space for length * firstStringLength;
		// If strings[0] is null, then its length is defined as 4, since that's the
		// length of "null".
		final int firstStringLength = strings[0] != null ? strings[0].length() : 4;
		StringBuilder buf = new StringBuilder( length * firstStringLength )
				.append( strings[0] );
		for ( int i = 1; i < length; i++ ) {
			buf.append( seperator ).append( strings[i] );
		}
		return buf.toString();
	}
