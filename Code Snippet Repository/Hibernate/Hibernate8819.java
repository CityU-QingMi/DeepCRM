	@Test
	@TestForIssue( jiraKey = "" )
	public void testCollectionJoinTableNamingJpaCompliantStrategy() {
		// Even in 4.3, with JPA compliant naming, Hibernate creates an unusable table...

		final MetadataSources metadataSources = new MetadataSources();
		try {
			metadataSources.addAnnotatedClass( Input.class );
			metadataSources.addAnnotatedClass( Ptx.class );

			final Metadata metadata = metadataSources.getMetadataBuilder()
					.applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
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
