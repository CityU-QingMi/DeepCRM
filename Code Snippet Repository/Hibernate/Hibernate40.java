	@Test
	public void test_bootstrap_bootstrap_native_registry_StandardServiceRegistryBuilder_example_2() {
		//tag::bootstrap-bootstrap-native-registry-StandardServiceRegistryBuilder-example[]

		// An example using an explicitly built BootstrapServiceRegistry
		BootstrapServiceRegistry bootstrapRegistry =
			new BootstrapServiceRegistryBuilder().build();

		StandardServiceRegistryBuilder standardRegistryBuilder =
			new StandardServiceRegistryBuilder( bootstrapRegistry );
		//end::bootstrap-bootstrap-native-registry-StandardServiceRegistryBuilder-example[]
	}
