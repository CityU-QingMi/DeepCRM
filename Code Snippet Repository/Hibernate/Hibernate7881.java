	@Test
	public void testIt() {
		MetadataSources metadataSources = new MetadataSources()
			.addResource("org/hibernate/test/hbm/version/Mappings.hbm.xml");

		try {
			metadataSources.buildMetadata();
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
