	public void addWhere( String leftAlias, String left, String op, QueryBuilder right) {
		final StringBuilder expression = new StringBuilder();

		if ( leftAlias != null ) {
			expression.append( leftAlias ).append( "." );
		}

		expression.append( left );

		expression.append( " " ).append( op ).append( " " );

		expression.append( "(" );
		right.build( expression, localQueryParamValues );
		expression.append( ")" );

		expressions.add( expression.toString() );
	}
