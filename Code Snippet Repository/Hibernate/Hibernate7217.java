	@Test
	public void testCacheOnNonRootEntity() {
		Map settings = new HashMap();
		settings.put( Environment.CACHE_REGION_FACTORY, CachingRegionFactory.class.getName() );
		settings.put( AvailableSettings.JPA_SHARED_CACHE_MODE, SharedCacheMode.ENABLE_SELECTIVE );

		ServiceRegistryImplementor serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
				.applySettings( settings )
				.build();

		Triggerable triggerable = logInspection.watchForLogMessages( "HHH000482" );

		Metadata metadata = new MetadataSources( serviceRegistry )
				.addAnnotatedClass( ABase.class )
				.addAnnotatedClass( AEntity.class )
				.buildMetadata();

		assertTrue( triggerable.wasTriggered() );
		assertNull( ( metadata.getEntityBinding( AEntity.class.getName() ) ).getCacheConcurrencyStrategy() );

		serviceRegistry.destroy();
	}
