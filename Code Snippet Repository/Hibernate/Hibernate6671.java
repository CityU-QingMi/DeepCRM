	@Test
	public void testMisplacedImmutableAnnotation() {
		MetadataSources metadataSources = new MetadataSources().addAnnotatedClass( Foobar.class );
		try {
			metadataSources.buildMetadata();
			fail( "Expecting exception due to misplaced @Immutable annotation");
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
