	protected AbstractBatchImpl(BatchKey key, JdbcCoordinator jdbcCoordinator) {
		if ( key == null ) {
			throw new IllegalArgumentException( "batch key cannot be null" );
		}
		if ( jdbcCoordinator == null ) {
			throw new IllegalArgumentException( "JDBC coordinator cannot be null" );
		}
		this.key = key;
		this.jdbcCoordinator = jdbcCoordinator;

		final JdbcServices jdbcServices = jdbcCoordinator.getJdbcSessionOwner()
				.getJdbcSessionContext()
				.getServiceRegistry()
				.getService( JdbcServices.class );

		this.sqlStatementLogger = jdbcServices.getSqlStatementLogger();
		this.sqlExceptionHelper = jdbcServices.getSqlExceptionHelper();
	}
