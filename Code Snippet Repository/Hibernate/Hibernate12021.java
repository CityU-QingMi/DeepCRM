	@Override
	public void configure(Map configurationValues) {
		if ( connectionProvider == null ) {
			@SuppressWarnings("unchecked")
			Map<String, Object> settings = new HashMap<>( configurationValues );
			settings.remove( AvailableSettings.CONNECTION_PROVIDER );
			connectionProvider = ConnectionProviderInitiator.INSTANCE.initiateService(
					settings,
					serviceRegistry
			);
			if ( connectionProvider instanceof Configurable ) {
				Configurable configurableConnectionProvider = (Configurable) connectionProvider;
				configurableConnectionProvider.configure( settings );
			}
		}
	}
