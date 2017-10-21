	@SuppressWarnings( {""})
	public StandardServiceRegistryImpl(
			boolean autoCloseRegistry,
			BootstrapServiceRegistry bootstrapServiceRegistry,
			List<StandardServiceInitiator> serviceInitiators,
			List<ProvidedService> providedServices,
			Map<?, ?> configurationValues) {
		super( bootstrapServiceRegistry, autoCloseRegistry );

		this.configurationValues = configurationValues;

		// process initiators
		for ( ServiceInitiator initiator : serviceInitiators ) {
			createServiceBinding( initiator );
		}

		// then, explicitly provided service instances
		for ( ProvidedService providedService : providedServices ) {
			createServiceBinding( providedService );
		}
	}
