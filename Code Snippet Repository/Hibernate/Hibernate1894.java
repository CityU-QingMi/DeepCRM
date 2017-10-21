	private String getAlias(String expression) {
		// remove any function arguments, if any exist.
		// 'cast(tab1.col1 as varchar(255)) as col1' -> 'cast as col1'
		// 'cast(tab1.col1 as varchar(255)) col1 -> 'cast col1'
		// 'cast(tab1.col1 as varchar(255))' -> 'cast'
		expression = expression.replaceFirst( "(\\((.)*\\))", "" ).trim();

		// This will match any text provided with:
		// 		columnName [[as] alias]
		final Matcher matcher = ALIAS_PATTERN.matcher( expression );

		String alias = null;
		if ( matcher.find() && matcher.groupCount() > 1 ) {
			// default to the alias after 'as' if detected
			alias = matcher.group( 3 );
			if ( alias == null ) {
				// use the clause which has on proceeding 'as' fragment.
				alias = matcher.group( 0 );
			}
		}

		return ( alias != null ? alias.trim() : null );
	}
