	public LogicalConnectionManagedImpl(
			JdbcConnectionAccess jdbcConnectionAccess,
			JdbcSessionContext jdbcSessionContext,
			ResourceRegistry resourceRegistry) {
		this.jdbcConnectionAccess = jdbcConnectionAccess;
		this.observer = jdbcSessionContext.getObserver();
		this.resourceRegistry = resourceRegistry;

		this.connectionHandlingMode = determineConnectionHandlingMode(
				jdbcSessionContext.getPhysicalConnectionHandlingMode(),
				jdbcConnectionAccess

		);

		this.sqlExceptionHelper = jdbcSessionContext.getServiceRegistry()
				.getService( JdbcServices.class )
				.getSqlExceptionHelper();

		if ( connectionHandlingMode.getAcquisitionMode() == ConnectionAcquisitionMode.IMMEDIATELY ) {
			acquireConnectionIfNeeded();
		}

		this.providerDisablesAutoCommit = jdbcSessionContext.doesConnectionProviderDisableAutoCommit();
		if ( providerDisablesAutoCommit ) {
			log.debug(
					"`hibernate.connection.provider_disables_autocommit` was enabled.  This setting should only be " +
							"enabled when you are certain that the Connections given to Hibernate by the " +
							"ConnectionProvider have auto-commit disabled.  Enabling this setting when the " +
							"Connections do not have auto-commit disabled will lead to Hibernate executing " +
							"SQL operations outside of any JDBC/SQL transaction."
			);
		}
	}
