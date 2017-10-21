	@Test
	@TestForIssue( jiraKey = "" )
	public void testNonAggregatedCompositeId() {
		// HHH-9913 reports a NPE when bootstrapping a SF with non-aggregated composite identifiers
		// in org.hibernate.cache.internal.CacheDataDescriptionImpl#decode
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.USE_SECOND_LEVEL_CACHE, true )
				.build();

		try {
			new MetadataSources( ssr )
					.addAnnotatedClass( It.class )
					.getMetadataBuilder()
					.applySharedCacheMode( SharedCacheMode.ENABLE_SELECTIVE )
					.build()
					.buildSessionFactory();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
