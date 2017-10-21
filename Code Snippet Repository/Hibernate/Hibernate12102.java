	public static void toWriter(Metadata metadata, Writer writer) {
		final ServiceRegistry serviceRegistry = ( (MetadataImplementor) metadata ).getMetadataBuildingOptions().getServiceRegistry();
		final Map settings = serviceRegistry.getService( ConfigurationService.class ).getSettings();
		settings.put( AvailableSettings.HBM2DDL_SCRIPTS_ACTION, Action.CREATE );
		settings.put( AvailableSettings.HBM2DDL_SCRIPTS_CREATE_TARGET, writer );
		SchemaManagementToolCoordinator.process(
				metadata,
				serviceRegistry,
				settings,
				DelayedDropRegistryNotAvailableImpl.INSTANCE
		);
	}
