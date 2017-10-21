	public void addWhereWithNamedParam(String left, boolean addAlias, String op, String paramName) {
		final StringBuilder expression = new StringBuilder();

		if ( addAlias ) {
			expression.append( alias ).append( "." );
		}
		expression.append( left );
		expression.append( " " ).append( op ).append( " " );
		expression.append( ":" ).append( paramName );

		expressions.add( expression.toString() );
	}
