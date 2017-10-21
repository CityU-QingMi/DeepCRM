	private static int getStartingPositionFor(String[] tokens, StringBuilder templateQuery) {
		templateQuery.append( tokens[0] );
		if ( !"select".equals( tokens[0].toLowerCase(Locale.ROOT) ) ) {
			return 1;
		}

		// select-range is terminated by declaration of "from"
		for ( int i = 1; i < tokens.length; i++ ) {
			if ( "from".equals( tokens[i].toLowerCase(Locale.ROOT) ) ) {
				return i;
			}
			templateQuery.append( tokens[i] );
		}
		return tokens.length;
	}
