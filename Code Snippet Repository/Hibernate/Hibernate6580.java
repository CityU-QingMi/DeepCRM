	@Test
	public void testXmlOverride() {
		Configuration config = new Configuration();
		config.addAnnotatedClass( Customer5.class );
		config.addAnnotatedClass( Order.class );
		config.addAnnotatedClass( Country.class );
		InputStream is = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream( "org/hibernate/test/annotations/fetchprofile/mappings.hbm.xml" );
		config.addInputStream( is );
		SessionFactoryImplementor sessionImpl = ( SessionFactoryImplementor ) config.buildSessionFactory(
				serviceRegistry
		);

		assertTrue(
				"fetch profile not parsed properly",
				sessionImpl.containsFetchProfileDefinition( "orders-profile" )
		);
		sessionImpl.close();

		// now the same with no xml
		final MetadataSources metadataSources = new MetadataSources()
				.addAnnotatedClass( Customer5.class )
				.addAnnotatedClass( Order.class )
				.addAnnotatedClass( Country.class );

		try {
			metadataSources.buildMetadata();
			fail();
		}
		catch ( MappingException e ) {
			log.trace("success");
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
