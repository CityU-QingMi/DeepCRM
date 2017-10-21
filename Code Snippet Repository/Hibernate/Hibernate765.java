	public static DatabaseIdentifier toIdentifier(String text) {
		if ( StringHelper.isEmpty( text ) ) {
			return null;
		}
		else if ( isQuoted( text ) ) {
			// exclude the quotes from text
			final String unquotedtext = text.substring( 1, text.length() - 1 );
			return new DatabaseIdentifier( unquotedtext );
		}
		else {
			return new DatabaseIdentifier( text );
		}
	}
