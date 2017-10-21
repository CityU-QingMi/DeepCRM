	@Test
	@SuppressWarnings("")
	public void testSpreadNaturalIdDeclarationGivesMappingException() {
		final MetadataSources metadataSources = new MetadataSources()
			.addAnnotatedClass( Principal.class )
			.addAnnotatedClass( User.class );
		try {

			metadataSources.buildMetadata();
			fail( "Expected binders to throw an exception" );
		}
		catch (AnnotationException expected) {
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
