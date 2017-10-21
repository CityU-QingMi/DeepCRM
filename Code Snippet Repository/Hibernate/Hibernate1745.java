	public String renderOrderByElement(String expression, String collation, String order, NullPrecedence nulls) {
		final StringBuilder orderByElement = new StringBuilder( expression );
		if ( collation != null ) {
			orderByElement.append( " " ).append( collation );
		}
		if ( order != null ) {
			orderByElement.append( " " ).append( order );
		}
		if ( nulls != NullPrecedence.NONE ) {
			orderByElement.append( " nulls " ).append( nulls.name().toLowerCase( Locale.ROOT ) );
		}
		return orderByElement.toString();
	}
