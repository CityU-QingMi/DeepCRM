		@Override
		public void execute(Connection connection) {
			try {
				Statement statement = connection.createStatement();
				try {
					statement.executeUpdate( logStatement(factory, idTableInfo.getIdTableCreationStatement()) );
					factory.getServiceRegistry()
							.getService( JdbcServices.class )
							.getSqlExceptionHelper()
							.handleAndClearWarnings( statement, WARNING_HANDLER );
				}
				finally {
					try {
						statement.close();
					}
					catch( Throwable ignore ) {
						// ignore
					}
				}
			}
			catch( Exception e ) {
				log.debug( "unable to create temporary id table [" + e.getMessage() + "]" );
			}
		}
