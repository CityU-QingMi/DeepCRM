	@Test
	public void testInvalidMapping() {
		MetadataSources metadataSources = new MetadataSources( )
			.addAnnotatedClass( TheEntity.class );
		try {
			metadataSources.buildMetadata();
			fail( "Was expecting failure" );
		}
		catch (AnnotationException ignore) {
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
