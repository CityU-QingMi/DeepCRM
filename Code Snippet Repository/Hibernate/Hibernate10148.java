	public void addWhereWithFunction(String alias, String left, String leftFunction, String op, Object paramValue){
		final String paramName = generateQueryParam();
		localQueryParamValues.put( paramName, paramValue );
		
		final StringBuilder expression = new StringBuilder();
		
		expression.append( leftFunction ).append( "(" );
		expression.append( alias ).append( "." );
		expression.append( left ).append( ")" );
		expression.append( " " ).append( op ).append( " " );
		expression.append( ":" ).append( paramName );
		
		expressions.add( expression.toString() );
	}
