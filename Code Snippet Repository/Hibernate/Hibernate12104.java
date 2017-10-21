	public static void drop(
			MetadataImplementor metadata,
			StandardServiceRegistry serviceRegistry,
			Connection connection) {
		final Map settings = serviceRegistry.getService( ConfigurationService.class ).getSettings();
		settings.put( AvailableSettings.HBM2DDL_DATABASE_ACTION, Action.DROP );
		settings.put( AvailableSettings.HBM2DDL_CONNECTION, connection );
		SchemaManagementToolCoordinator.process(
				metadata,
				serviceRegistry,
				settings,
				DelayedDropRegistryNotAvailableImpl.INSTANCE
		);

	}
