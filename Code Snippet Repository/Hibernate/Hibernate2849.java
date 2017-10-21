	@Override
	protected void initialize(MetadataBuildingOptions buildingOptions, SessionFactoryOptions sessionFactoryOptions) {
		final StandardServiceRegistry serviceRegistry = buildingOptions.getServiceRegistry();
		final ConfigurationService configService = serviceRegistry.getService( ConfigurationService.class );
		this.dropIdTables = configService.getSetting(
				DROP_ID_TABLES,
				StandardConverters.BOOLEAN,
				false
		);
	}
