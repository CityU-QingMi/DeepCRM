	@Test
	public void testNaturalIdCached() throws Exception {
		saveSomeCitizens();

		// Clear the cache before the transaction begins
		cleanupCache();
		TIME_SERVICE.advance(1);

		withTxSession(s -> {
			State france = ReadWriteTest.this.getState(s, "Ile de France");
			Criteria criteria = s.createCriteria( Citizen.class );
			criteria.add( Restrictions.naturalId().set( "ssn", "1234" ).set( "state", france ) );
			criteria.setCacheable( true );

			Statistics stats = sessionFactory().getStatistics();
			stats.setStatisticsEnabled( true );
			stats.clear();
			assertEquals(
					"Cache hits should be empty", 0, stats
					.getNaturalIdCacheHitCount()
			);

			// first query
			List results = criteria.list();
			assertEquals( 1, results.size() );
			assertEquals( "NaturalId Cache Hits", 0, stats.getNaturalIdCacheHitCount() );
			assertEquals( "NaturalId Cache Misses", 1, stats.getNaturalIdCacheMissCount() );
			assertEquals( "NaturalId Cache Puts", 1, stats.getNaturalIdCachePutCount() );
			assertEquals( "NaturalId Cache Queries", 1, stats.getNaturalIdQueryExecutionCount() );

			// query a second time - result should be cached in session
			criteria.list();
			assertEquals( "NaturalId Cache Hits", 0, stats.getNaturalIdCacheHitCount() );
			assertEquals( "NaturalId Cache Misses", 1, stats.getNaturalIdCacheMissCount() );
			assertEquals( "NaturalId Cache Puts", 1, stats.getNaturalIdCachePutCount() );
			assertEquals( "NaturalId Cache Queries", 1, stats.getNaturalIdQueryExecutionCount() );

			// cleanup
			markRollbackOnly(s);
		});
	}
