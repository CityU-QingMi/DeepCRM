	@Test
	@TestForIssue( jiraKey = "" )
	public void testCollectionJoinTableNamingLegacyJpaStrategy() {
		final MetadataSources metadataSources = new MetadataSources();
		try {
			metadataSources.addAnnotatedClass( Input.class );
			metadataSources.addAnnotatedClass( Ptx.class );

			final Metadata metadata = metadataSources.getMetadataBuilder()
					.applyImplicitNamingStrategy( ImplicitNamingStrategyLegacyJpaImpl.INSTANCE )
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
