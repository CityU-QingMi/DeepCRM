	private String getUnquotedAliasString(String sqlIdentifier, int quoteType) {
		String unquoted = sqlIdentifier;
		if ( quoteType >= 0 ) {
			//if the identifier is quoted, remove the quotes
			unquoted = unquoted.substring( 1, unquoted.length()-1 );
		}
		if ( unquoted.length() > length ) {
			//truncate the identifier to the max alias length, less the suffix length
			unquoted = unquoted.substring(0, length);
		}
		return ( suffix == null ) ? unquoted : unquoted + suffix;
	}
