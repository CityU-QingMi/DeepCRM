	@Override
	public void configure(Map configurationValues) {
		Properties connectionSettings = new Properties();
		transferSetting( Environment.DRIVER, configurationValues, connectionSettings );
		transferSetting( Environment.URL, configurationValues, connectionSettings );
		transferSetting( Environment.USER, configurationValues, connectionSettings );
		transferSetting( Environment.PASS, configurationValues, connectionSettings );
		transferSetting( Environment.ISOLATION, configurationValues, connectionSettings );
		Properties passThroughSettings = ConnectionProviderInitiator.getConnectionProperties( configurationValues );
		if ( passThroughSettings != null ) {
			for ( String setting : passThroughSettings.stringPropertyNames() ) {
				transferSetting( Environment.CONNECTION_PREFIX + '.' + setting, configurationValues, connectionSettings );
			}
		}
		connectionSettings.setProperty( Environment.AUTOCOMMIT, "false" );

		delegate = new DriverManagerConnectionProviderImpl();
		delegate.configure( connectionSettings );
	}
