	@Test
	@TestForIssue( jiraKey = "" )
	public void testNaturalIdCacheStatisticsReset() {
		final String naturalIdCacheRegion = "hibernate.test.org.hibernate.test.naturalid.mutable.cached.Another##NaturalId";
		sessionFactory().getStatistics().clear();

		Session session = openSession();
		session.beginTransaction();
		final Another it = new Another( "IT");
		session.save( it );
		session.getTransaction().commit();
		session.close();

		NaturalIdCacheStatistics statistics = sessionFactory().getStatistics().getNaturalIdCacheStatistics( naturalIdCacheRegion );
		assertEquals( 1, statistics.getPutCount() );

		sessionFactory().getStatistics().clear();

		// Refresh statistics reference.
		statistics = sessionFactory().getStatistics().getNaturalIdCacheStatistics( naturalIdCacheRegion );
		assertEquals( 0, statistics.getPutCount() );

		session = openSession();
		session.beginTransaction();
		session.delete( it );
		session.getTransaction().commit();
		session.clear();
	}
