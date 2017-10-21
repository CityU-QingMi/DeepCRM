	public static String collapseQualifier(String qualifier, boolean includeDots) {
		StringTokenizer tokenizer = new StringTokenizer( qualifier, "." );
		String collapsed = Character.toString( tokenizer.nextToken().charAt( 0 ) );
		while ( tokenizer.hasMoreTokens() ) {
			if ( includeDots ) {
				collapsed += '.';
			}
			collapsed += tokenizer.nextToken().charAt( 0 );
		}
		return collapsed;
	}
