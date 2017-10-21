	@Test
	public void test_basic_custom_type_register_BasicType_example() {
		try {
			//tag::basic-custom-type-register-BasicType-example[]
			ServiceRegistry standardRegistry =
				new StandardServiceRegistryBuilder().build();

			MetadataSources sources = new MetadataSources( standardRegistry );

			MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

			metadataBuilder.applyBasicType( BitSetType.INSTANCE );
			//end::basic-custom-type-register-BasicType-example[]
		}
		catch (Exception ignore) {

		}
	}
