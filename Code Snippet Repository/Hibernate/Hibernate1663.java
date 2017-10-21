	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		final Dialect dialect = criteriaQuery.getFactory().getDialect();
		final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
		if ( columns.length != 1 ) {
			throw new HibernateException( "ilike may only be used with single-column properties" );
		}
		if ( dialect instanceof PostgreSQLDialect || dialect instanceof PostgreSQL81Dialect) {
			return columns[0] + " ilike ?";
		}
		else {
			return dialect.getLowercaseFunction() + '(' + columns[0] + ") like ?";
		}
	}
