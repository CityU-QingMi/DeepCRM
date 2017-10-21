	public static Identifier toIdentifier(String text, boolean quote) {
		if ( StringHelper.isEmpty( text ) ) {
			return null;
		}
		final String trimmedText = text.trim();
		if ( isQuoted( trimmedText ) ) {
			final String bareName = trimmedText.substring( 1, trimmedText.length() - 1 );
			return new Identifier( bareName, true );
		}
		else {
			return new Identifier( trimmedText, quote );
		}
	}
