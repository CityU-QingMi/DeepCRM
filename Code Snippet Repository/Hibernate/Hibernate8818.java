	@Test
	@TestForIssue( jiraKey = "" )
	public void testCollectionJoinTableNamingLegacyHbmStrategy() {
		final MetadataSources metadataSources = new MetadataSources();
		try {
			metadataSources.addAnnotatedClass( Input.class );
			metadataSources.addAnnotatedClass( Ptx.class );

			final Metadata metadata = metadataSources.getMetadataBuilder()
					.applyImplicitNamingStrategy( ImplicitNamingStrategyLegacyHbmImpl.INSTANCE )
					.build();

			Collection inputs1Mapping = metadata.getCollectionBinding( Ptx.class.getName() + ".inputs1" );
			assertEquals( "ptx_inputs1", inputs1Mapping.getCollectionTable().getName() );

			Collection inputs2Mapping = metadata.getCollectionBinding( Ptx.class.getName() + ".inputs2" );
			assertEquals( "ptx_inputs2", inputs2Mapping.getCollectionTable().getName() );
		}
		finally {
			ServiceRegistry metaServiceRegistry = metadataSources.getServiceRegistry();
			if(metaServiceRegistry instanceof BootstrapServiceRegistry ) {
				BootstrapServiceRegistryBuilder.destroy( metaServiceRegistry );
			}
		}
	}
