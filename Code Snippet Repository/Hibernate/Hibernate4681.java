	private static void appendTokens(StringBuilder buf, Iterator iter) {
		boolean lastSpaceable = true;
		boolean lastQuoted = false;
		while ( iter.hasNext() ) {
			String token = (String) iter.next();
			boolean spaceable = !DONT_SPACE_TOKENS.contains( token );
			boolean quoted = token.startsWith( "'" );
			if ( spaceable && lastSpaceable ) {
				if ( !quoted || !lastQuoted ) {
					buf.append( ' ' );
				}
			}
			lastSpaceable = spaceable;
			buf.append( token );
			lastQuoted = token.endsWith( "'" );
		}
	}
