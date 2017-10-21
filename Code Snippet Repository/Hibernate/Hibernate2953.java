	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
		this.dialect = serviceRegistry.getService( JdbcEnvironment.class ).getDialect();
		final ConfigurationService configService = serviceRegistry.getService( ConfigurationService.class );

		final boolean useNewIdentifierGenerators = configService.getSetting(
				AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS,
				StandardConverters.BOOLEAN,
				true
		);

		if(!useNewIdentifierGenerators) {
			register( "sequence", SequenceGenerator.class );
		}
	}
