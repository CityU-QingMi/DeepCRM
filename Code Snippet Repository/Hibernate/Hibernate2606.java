	private SQLFunction resolveFunction() {
		if ( sqlFunction == null ) {
			final String name = getText();
			sqlFunction = getSessionFactoryHelper().findSQLFunction( getText() );
			if ( sqlFunction == null ) {
				LOG.unableToResolveAggregateFunction( name );
				sqlFunction = new StandardSQLFunction( name );
			}
		}
		return sqlFunction;
	}
