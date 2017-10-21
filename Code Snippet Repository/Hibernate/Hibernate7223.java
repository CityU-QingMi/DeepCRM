	@Test
	@TestForIssue( jiraKey = "" )
	public void testNonAggregatedCompositeIdWithPkClass() {
		// HHH-9913 reports a NPE when bootstrapping a SF with non-aggregated composite identifiers
		// in org.hibernate.cache.internal.CacheDataDescriptionImpl#decode
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			new MetadataSources( ssr )
					.addAnnotatedClass( ItWithPkClass.class )
					.getMetadataBuilder()
					.applySharedCacheMode( SharedCacheMode.ENABLE_SELECTIVE )
					.build()
					.buildSessionFactory();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
