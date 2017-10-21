	public void executeIdTableCreationStatements(
			List<String> creationStatements,
			JdbcServices jdbcServices,
			JdbcConnectionAccess connectionAccess) {
		try {
			Connection connection;
			try {
				connection = connectionAccess.obtainConnection();
			}
			catch (UnsupportedOperationException e) {
				// assume this comes from org.hibernate.engine.jdbc.connections.internal.UserSuppliedConnectionProviderImpl
				log.debug( "Unable to obtain JDBC connection; assuming ID tables already exist or wont be needed" );
				return;
			}

			try {
				// TODO: session.getTransactionCoordinator().getJdbcCoordinator().getStatementPreparer().createStatement();
				Statement statement = connection.createStatement();
				for ( String creationStatement : creationStatements ) {
					try {
						jdbcServices.getSqlStatementLogger().logStatement( creationStatement );
						// TODO: ResultSetExtractor
						statement.execute( creationStatement );
					}
					catch (SQLException e) {
						log.debugf( "Error attempting to export id-table [%s] : %s", creationStatement, e.getMessage() );
					}
				}

				// TODO
//				session.getTransactionCoordinator().getJdbcCoordinator().release( statement );
				statement.close();
			}
			catch (SQLException e) {
				log.error( "Unable to use JDBC Connection to create Statement", e );
			}
			finally {
				try {
					connectionAccess.releaseConnection( connection );
				}
				catch (SQLException ignore) {
				}
			}
		}
		catch (SQLException e) {
			log.error( "Unable obtain JDBC Connection", e );
		}
	}
