	@Test
	@TestForIssue( jiraKey = "" )
	@FailureExpected( jiraKey = "" )
	public void testExplicitDefault() {

		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.DEFAULT_CACHE_CONCURRENCY_STRATEGY, "read-only" )
				.build();
		try {
			assertEquals(
					"read-only",
					ssr.getService( ConfigurationService.class ).getSettings().get(
							AvailableSettings.DEFAULT_CACHE_CONCURRENCY_STRATEGY
					)
			);
			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( TheEntity.class )
					.buildMetadata();
			assertEquals(
					AccessType.READ_ONLY,
					metadata.getMetadataBuildingOptions().getMappingDefaults().getImplicitCacheAccessType()
			);
			final SessionFactoryImplementor sf = (SessionFactoryImplementor) metadata.buildSessionFactory();
			try {
				final EntityPersister persister = sf.getMetamodel().entityPersister( TheEntity.class.getName() );
				assertNotNull( persister.getCacheAccessStrategy() );
			}
			finally {
				sf.close();
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
