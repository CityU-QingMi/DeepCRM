	@Test
	public void test_basic_custom_type_register_UserType_example() {
		try {
			//tag::basic-custom-type-register-UserType-example[]
			ServiceRegistry standardRegistry =
				new StandardServiceRegistryBuilder().build();

			MetadataSources sources = new MetadataSources( standardRegistry );

			MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

			metadataBuilder.applyBasicType( BitSetUserType.INSTANCE, "bitset" );
			//end::basic-custom-type-register-UserType-example[]
		}
		catch (Exception ignore) {

		}
	}
