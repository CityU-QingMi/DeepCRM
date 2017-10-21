		@Override
		public void execute(Connection connection) {
			try {
				Statement statement = connection.createStatement();
				try {
					statement.executeUpdate( logStatement( factory, idTableInfo.getIdTableDropStatement() ) );
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
				log.warn( "unable to drop temporary id table after use [" + e.getMessage() + "]" );
			}
		}
