	public static ServiceRegistryTestingImpl forUnitTesting(Map settings) {
		return new ServiceRegistryTestingImpl(
				true,
				new BootstrapServiceRegistryBuilder().build(),
				StandardServiceInitiators.LIST,
				Arrays.asList(
						dialectFactoryService(),
						connectionProviderService()
				),
				settings
		);
	}
