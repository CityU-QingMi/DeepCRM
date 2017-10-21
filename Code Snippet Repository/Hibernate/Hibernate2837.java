	public AbstractCteValuesListBulkIdHandler(
			SessionFactoryImplementor sessionFactory, HqlSqlWalker walker,
			String catalog, String schema) {
		super( sessionFactory, walker );
		Dialect dialect = sessionFactory.getServiceRegistry().getService( JdbcServices.class ).getDialect();
		if ( !dialect.supportsNonQueryWithCTE() ) {
			throw new UnsupportedOperationException(
					"The " + getClass().getSimpleName() +
							" can only be used with Dialects that support CTE that can take UPDATE or DELETE statements as well!"
			);
		}
		if ( !dialect.supportsValuesList() ) {
			throw new UnsupportedOperationException(
					"The " + getClass().getSimpleName() +
							" can only be used with Dialects that support VALUES lists!"
			);
		}
		if ( !dialect.supportsRowValueConstructorSyntaxInInList() ) {
			throw new UnsupportedOperationException(
					"The " + getClass().getSimpleName() +
							" can only be used with Dialects that support IN clause row-value expressions (for composite identifiers)!"
			);
		}

		this.jdbcEnvironment = sessionFactory.getServiceRegistry().getService(
				JdbcServices.class ).getJdbcEnvironment();
		this.catalog = catalog;
		this.schema = schema;
	}
