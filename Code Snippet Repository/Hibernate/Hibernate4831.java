	@Override
	public void doValidation(Metadata metadata, ExecutionOptions options) {
		final JdbcContext jdbcContext = tool.resolveJdbcContext( options.getConfigurationValues() );

		final DdlTransactionIsolator isolator = tool.getDdlTransactionIsolator( jdbcContext );

		final DatabaseInformation databaseInformation = Helper.buildDatabaseInformation(
				tool.getServiceRegistry(),
				isolator,
				metadata.getDatabase().getDefaultNamespace().getName()
		);

		try {
			performValidation( metadata, databaseInformation, options, jdbcContext.getDialect() );
		}
		finally {
			try {
				databaseInformation.cleanup();
			}
			catch (Exception e) {
				log.debug( "Problem releasing DatabaseInformation : " + e.getMessage() );
			}

			isolator.release();
		}
	}
