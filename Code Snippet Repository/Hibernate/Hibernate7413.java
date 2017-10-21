	@Override
	@SuppressWarnings("")
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		TestingJtaBootstrap.prepare( settings );
//		settings.put( Environment.TRANSACTION_STRATEGY, CMTTransactionFactory.class.getName() );
		settings.put( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, JtaTransactionCoordinatorBuilderImpl.class.getName() );
		settings.put( Environment.RELEASE_CONNECTIONS, ConnectionReleaseMode.AFTER_STATEMENT.toString() );
		settings.put( Environment.GENERATE_STATISTICS, "true" );
		settings.put( Environment.STATEMENT_BATCH_SIZE, "0" );
	}
