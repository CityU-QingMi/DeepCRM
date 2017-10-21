	public void addWhereWithParams(String alias, String left, String opStart, Object[] paramValues, String opEnd) {
		final StringBuilder expression = new StringBuilder();

		expression.append( alias ).append( "." ).append( left ).append( " " ).append( opStart );

		for ( int i = 0; i < paramValues.length; i++ ) {
			final Object paramValue = paramValues[i];
			final String paramName = generateQueryParam();
			localQueryParamValues.put( paramName, paramValue );
			expression.append( ":" ).append( paramName );

			if ( i != paramValues.length - 1 ) {
				expression.append( ", " );
			}
		}

		expression.append( opEnd );

		expressions.add( expression.toString() );
	}
