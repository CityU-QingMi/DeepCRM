	private static String join(String[] elements, char c) {
		if ( elements == null ) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		for ( String s : elements ) {
			sb.append( s ).append( c );
		}
		return sb.length() > 0 ? sb.substring( 0, sb.length() - 1 ) : "";
	}
