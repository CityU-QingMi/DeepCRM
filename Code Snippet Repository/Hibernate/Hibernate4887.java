	@Override
	public void releaseConnection(Connection connection) throws SQLException {
		if ( connection != this.jdbcConnection ) {
			throw new PersistenceException(
					String.format(
							"Connection [%s] passed back to %s was not the one obtained [%s] from it",
							connection,
							JdbcConnectionAccessConnectionProviderImpl.class.getName(),
							jdbcConnection
					)
			);
		}

		// Reset auto-commit
		if ( !wasInitiallyAutoCommit ) {
			try {
				if ( jdbcConnection.getAutoCommit() ) {
					jdbcConnection.setAutoCommit( false );
				}
			}
			catch (SQLException e) {
				log.info( "Was unable to reset JDBC connection to no longer be in auto-commit mode" );
			}
		}

		// Release the connection
		connectionProvider.closeConnection( jdbcConnection );
	}
