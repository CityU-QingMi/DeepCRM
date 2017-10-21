	public static DatabaseInformation buildDatabaseInformation(
			ServiceRegistry serviceRegistry,
			DdlTransactionIsolator ddlTransactionIsolator,
			Namespace.Name defaultNamespace) {
		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		try {
			return new DatabaseInformationImpl(
					serviceRegistry,
					jdbcEnvironment,
					ddlTransactionIsolator,
					defaultNamespace
			);
		}
		catch (SQLException e) {
			throw jdbcEnvironment.getSqlExceptionHelper().convert( e, "Unable to build DatabaseInformation" );
		}
	}
