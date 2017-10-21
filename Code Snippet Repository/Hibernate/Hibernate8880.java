	@Test
	@TestForIssue( jiraKey = "" )
	public void testToMapConversion() {
		sessionFactory().getStatistics().clear();

		final Session session = openSession();
		session.getTransaction().begin();
		final AllCached it = new AllCached( "IT" );
		session.save( it );
		session.getTransaction().commit();
		session.close();

		final NaturalIdCacheStatistics stats = sessionFactory().getStatistics().getNaturalIdCacheStatistics(
				"hibernate.test." + AllCached.class.getName() + "##NaturalId"
		);

		final Map entries = stats.getEntries();
		assertEquals( 1, entries.size() );
		final Serializable[] cacheKey = (Serializable[]) entries.keySet().iterator().next();
		assertEquals( 1, cacheKey.length );
		assertEquals( it.getName(), cacheKey[0] );
		assertNotNull( entries.get( cacheKey ) );
	}
