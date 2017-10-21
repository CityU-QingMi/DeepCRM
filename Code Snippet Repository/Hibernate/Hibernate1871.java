	@Override
	public String addQueryHints(String query, String hints) {
		Matcher matcher = QUERY_PATTERN.matcher( query );
		if ( matcher.matches() && matcher.groupCount() > 1 ) {
			String startToken = matcher.group( 1 );
			String endToken = matcher.group( 2 );

			return new StringBuilder( startToken )
					.append( " USE INDEX (" )
					.append( hints )
					.append( ") " )
					.append( endToken )
					.toString();
		}
		else {
			return query;
		}
	}
