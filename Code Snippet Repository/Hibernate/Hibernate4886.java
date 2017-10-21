	public JdbcConnectionAccessConnectionProviderImpl(ConnectionProvider connectionProvider) {
		this.connectionProvider = connectionProvider;

		try {
			this.jdbcConnection = connectionProvider.getConnection();
		}
		catch (SQLException e) {
			throw new PersistenceException( "Unable to obtain JDBC Connection", e );
		}

		boolean wasInitiallyAutoCommit;
		try {
			wasInitiallyAutoCommit = jdbcConnection.getAutoCommit();
			if ( !wasInitiallyAutoCommit ) {
				try {
					jdbcConnection.setAutoCommit( true );
				}
				catch (SQLException e) {
					throw new PersistenceException(
							String.format(
									"Could not set provided connection [%s] to auto-commit mode" +
											" (needed for schema generation)",
									jdbcConnection
							),
							e
					);
				}
			}
		}
		catch (SQLException ignore) {
			wasInitiallyAutoCommit = false;
		}

		log.debugf( "wasInitiallyAutoCommit=%s", wasInitiallyAutoCommit );
		this.wasInitiallyAutoCommit = wasInitiallyAutoCommit;
	}
