	public String toAliasString(String sqlIdentifier) {
		char begin = sqlIdentifier.charAt(0);
		int quoteType = Dialect.QUOTE.indexOf(begin);
		String unquoted = getUnquotedAliasString(sqlIdentifier, quoteType);
		if ( quoteType >= 0 ) {
			char endQuote = Dialect.CLOSED_QUOTE.charAt(quoteType);
			return begin + unquoted + endQuote;
		}
		else {
			return unquoted;
		}
	}
