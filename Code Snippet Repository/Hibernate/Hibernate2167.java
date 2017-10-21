	private String formatCommentOn(String sql) {
		final StringBuilder result = new StringBuilder( 60 ).append( INITIAL_LINE );
		final StringTokenizer tokens = new StringTokenizer( sql, " '[]\"", true );

		boolean quoted = false;
		while ( tokens.hasMoreTokens() ) {
			final String token = tokens.nextToken();
			result.append( token );
			if ( isQuote( token ) ) {
				quoted = !quoted;
			}
			else if ( !quoted ) {
				if ( "is".equals( token ) ) {
					result.append( OTHER_LINES );
				}
			}
		}

		return result.toString();
	}
