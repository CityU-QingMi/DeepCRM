	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
		final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
		final Type type = criteriaQuery.getTypeUsingProjection( criteria, propertyName );
		final StringBuilder fragment = new StringBuilder();

		if ( columns.length > 1 ) {
			fragment.append( '(' );
		}
		final SessionFactoryImplementor factory = criteriaQuery.getFactory();
		final int[] sqlTypes = type.sqlTypes( factory );
		for ( int i = 0; i < columns.length; i++ ) {
			final boolean lower = ignoreCase && (sqlTypes[i] == Types.VARCHAR || sqlTypes[i] == Types.CHAR || 
					sqlTypes[i] == Types.NVARCHAR || sqlTypes[i] == Types.NCHAR);
			if ( lower ) {
				fragment.append( factory.getDialect().getLowercaseFunction() ).append( '(' );
			}
			fragment.append( columns[i] );
			if ( lower ) {
				fragment.append( ')' );
			}

			fragment.append( getOp() ).append( "?" );
			if ( i < columns.length - 1 ) {
				fragment.append( " and " );
			}
		}
		if ( columns.length > 1 ) {
			fragment.append( ')' );
		}
		return fragment.toString();
	}
