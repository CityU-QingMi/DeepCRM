	@Override
	protected String toLeftSqlString(Criteria criteria, CriteriaQuery outerQuery) {
		final StringBuilder left = new StringBuilder( "(" );
		final String[] sqlColumnNames = new String[propertyNames.length];
		for ( int i = 0; i < sqlColumnNames.length; ++i ) {
			sqlColumnNames[i] = outerQuery.getColumn( criteria, propertyNames[i] );
		}
		left.append( StringHelper.join( ", ", sqlColumnNames ) );
		return left.append( ")" ).toString();
	}
