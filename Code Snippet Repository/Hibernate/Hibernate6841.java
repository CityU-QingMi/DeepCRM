	@Test
	public void testNaturalIdCached() {
		saveSomeCitizens();
		
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		State france = this.getState( s, "Ile de France" );
		Criteria criteria = s.createCriteria( Citizen.class );
		criteria.add( Restrictions.naturalId().set( "ssn", "1234" ).set( "state", france ) );
		criteria.setCacheable( true );
		
		this.cleanupCache();

		Statistics stats = sessionFactory().getStatistics();
		stats.setStatisticsEnabled( true );
		stats.clear();
		assertEquals( "Cache hits should be empty", 0, stats.getNaturalIdCacheHitCount() );
		assertEquals( "Cache puts should be empty", 0, stats.getNaturalIdCachePutCount() );

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
		tx.rollback();
		s.close();
	}
