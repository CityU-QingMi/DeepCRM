	@Test
	public void testUnsupportedFetchMode() {
		final MetadataSources metadataSources = new MetadataSources()
				.addAnnotatedClass( Customer4.class )
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
