	public static ServiceRegistryTestingImpl forUnitTesting() {
		return new ServiceRegistryTestingImpl(
				true,
				new BootstrapServiceRegistryBuilder().build(),
				StandardServiceInitiators.LIST,
				Arrays.asList(
						dialectFactoryService(),
						connectionProviderService()
				),
				Environment.getProperties()
		);
	}
