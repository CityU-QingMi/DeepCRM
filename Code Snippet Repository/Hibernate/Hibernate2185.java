	public ResultSetReturnImpl(JdbcCoordinator jdbcCoordinator) {
		this.jdbcCoordinator = jdbcCoordinator;

		final JdbcServices jdbcServices = jdbcCoordinator.getJdbcSessionOwner()
				.getJdbcSessionContext()
				.getServiceRegistry()
				.getService( JdbcServices.class );

		this.dialect = jdbcServices.getDialect();

		this.sqlStatementLogger = jdbcServices.getSqlStatementLogger();
		this.sqlExceptionHelper = jdbcServices.getSqlExceptionHelper();
	}
