	public static boolean startsWithEscapeCallTemplate(String sqlString) {
		if ( ! ( sqlString.startsWith( "{" ) && sqlString.endsWith( "}" ) ) ) {
			return false;
		}

		final int chopLocation = sqlString.indexOf( "call" );
		if ( chopLocation <= 0 ) {
			return false;
		}

		final String checkString = sqlString.substring( 1, chopLocation + 4 );
		final String fixture = "?=call";
		int fixturePosition = 0;
		boolean matches = true;
		final int max = checkString.length();
		for ( int i = 0; i < max; i++ ) {
			final char c = Character.toLowerCase( checkString.charAt( i ) );
			if ( Character.isWhitespace( c ) ) {
				continue;
			}
			if ( c == fixture.charAt( fixturePosition ) ) {
				fixturePosition++;
				continue;
			}
			matches = false;
			break;
		}

		return matches;
	}
