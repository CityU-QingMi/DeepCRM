	@Override
	public IsolationDelegate createIsolationDelegate() {
		final JdbcSessionOwner jdbcSessionOwner = transactionCoordinatorOwner.getJdbcSessionOwner();

		return new JtaIsolationDelegate(
				jdbcSessionOwner.getJdbcConnectionAccess(),
				jdbcSessionOwner.getJdbcSessionContext()
						.getServiceRegistry()
						.getService( JdbcServices.class )
						.getSqlExceptionHelper(),
				jtaPlatform.retrieveTransactionManager()
		);
	}
