	@Test
	public void test_bootstrap_bootstrap_native_registry_BootstrapServiceRegistry_example() {

		ClassLoader customClassLoader = Thread.currentThread().getContextClassLoader();
		Integrator customIntegrator = new BeanValidationIntegrator();

		//tag::bootstrap-bootstrap-native-registry-BootstrapServiceRegistry-example[]
		BootstrapServiceRegistryBuilder bootstrapRegistryBuilder =
			new BootstrapServiceRegistryBuilder();
		// add a custom ClassLoader
		bootstrapRegistryBuilder.applyClassLoader( customClassLoader );
		// manually add an Integrator
		bootstrapRegistryBuilder.applyIntegrator( customIntegrator );

		BootstrapServiceRegistry bootstrapRegistry = bootstrapRegistryBuilder.build();
		//end::bootstrap-bootstrap-native-registry-BootstrapServiceRegistry-example[]
	}
