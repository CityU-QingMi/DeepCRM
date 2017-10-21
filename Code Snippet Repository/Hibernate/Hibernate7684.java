	@Test
	public void basicTest() {
		logInspection.registerListener( LogListenerImpl.INSTANCE );

		MetadataSources metadataSources = new MetadataSources()
			.addResource( "org/hibernate/test/entitymode/dom4j/Car.hbm.xml" );
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
