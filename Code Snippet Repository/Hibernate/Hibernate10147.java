	public void addWhere(String aliasLeft, String left, String op, String aliasRight, String right) {
		final StringBuilder expression = new StringBuilder();

		if ( aliasLeft != null ) {
			expression.append( aliasLeft ).append( "." );
		}
		expression.append( left );

		expression.append( " " ).append( op ).append( " " );

		if ( aliasRight != null ) {
			expression.append( aliasRight ).append( "." );
		}
		expression.append( right );

		expressions.add( expression.toString() );
	}
