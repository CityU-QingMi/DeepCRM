	@Test
	public void testNaturalIdLoaderNotCached() {
		saveSomeCitizens();

		Session s = openSession();
		Transaction tx = s.beginTransaction();
		State france = this.getState( s, "Ile de France" );
		final NaturalIdLoadAccess naturalIdLoader = s.byNaturalId( Citizen.class );
		naturalIdLoader.using( "ssn", "1234" ).using( "state", france );

		//NaturalId cache gets populated during entity loading, need to clear it out
		this.cleanupCache();
		Statistics stats = sessionFactory().getStatistics();
		stats.setStatisticsEnabled( true );
		stats.clear();
		assertEquals( "NaturalId Cache Hits", 0, stats.getNaturalIdCacheHitCount() );
		assertEquals( "NaturalId Cache Misses", 0, stats.getNaturalIdCacheMissCount() );
		assertEquals( "NaturalId Cache Puts", 0, stats.getNaturalIdCachePutCount() );
		assertEquals( "NaturalId Cache Queries", 0, stats.getNaturalIdQueryExecutionCount() );

		// first query
		Citizen citizen = (Citizen)naturalIdLoader.load();
		assertNotNull( citizen );
		assertEquals( "NaturalId Cache Hits", 0, stats.getNaturalIdCacheHitCount() );
		assertEquals( "NaturalId Cache Misses", 1, stats.getNaturalIdCacheMissCount() );
		assertEquals( "NaturalId Cache Puts", 1, stats.getNaturalIdCachePutCount() );
		assertEquals( "NaturalId Cache Queries", 1, stats.getNaturalIdQueryExecutionCount() );

		// cleanup
		tx.rollback();
		s.close();
	}
