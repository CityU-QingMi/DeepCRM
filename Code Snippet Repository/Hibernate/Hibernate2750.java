	public static void appendEscapedMultibyteChars(String text, StringBuilder buf) {
		char[] chars = text.toCharArray();
		for ( char aChar : chars ) {
			if ( aChar > 256 ) {
				buf.append( "\\u" );
				buf.append( Integer.toHexString( aChar ) );
			}
			else {
				buf.append( aChar );
			}
		}
	}
