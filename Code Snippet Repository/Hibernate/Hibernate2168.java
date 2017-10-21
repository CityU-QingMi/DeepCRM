	private String formatAlterTable(String sql) {
		final StringBuilder result = new StringBuilder( 60 ).append( INITIAL_LINE );
		final StringTokenizer tokens = new StringTokenizer( sql, " (,)'[]\"", true );

		boolean quoted = false;
		while ( tokens.hasMoreTokens() ) {
			final String token = tokens.nextToken();
			if ( isQuote( token ) ) {
				quoted = !quoted;
			}
			else if ( !quoted ) {
				if ( isBreak( token ) ) {
					result.append( OTHER_LINES );
				}
			}
			result.append( token );
		}

		return result.toString();
	}
