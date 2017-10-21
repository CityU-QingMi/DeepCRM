	@Test
	@TestForIssue( jiraKey = "" )
	public void testCollectionJoinTableNamingBase() {
		// really the same as the JPA compliant tests; here we just pick up the default ImplicitNamingStrategy
		final MetadataSources metadataSources = new MetadataSources();
		try {
			metadataSources.addAnnotatedClass( Input.class );
			metadataSources.addAnnotatedClass( Ptx.class );

			final Metadata metadata = metadataSources.getMetadataBuilder()
					.build();

			assertSameTableUsed( metadata );
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
