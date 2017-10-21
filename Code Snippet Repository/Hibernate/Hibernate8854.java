	@Test
	@TestForIssue(jiraKey = "")
	public void testNaturalIdCacheEntry() {
		doInHibernate( this::sessionFactory, session -> {
			Person person = new Person();
			person.setName( "John Doe" );
			session.persist( person );
		} );
		doInHibernate( this::sessionFactory, session -> {
			assertEquals(0, sessionFactory().getStatistics().getSecondLevelCacheHitCount());
			assertEquals(0, sessionFactory().getStatistics().getNaturalIdCacheHitCount());
			Person person = session.bySimpleNaturalId( Person.class )
					.load( "John Doe" );
			assertEquals(0, sessionFactory().getStatistics().getSecondLevelCacheHitCount());
			assertEquals(1, sessionFactory().getStatistics().getNaturalIdCacheHitCount());
		} );
		doInHibernate( this::sessionFactory, session -> {
			Person person = session.bySimpleNaturalId( Person.class )
					.load( "John Doe" );
			assertEquals(1, sessionFactory().getStatistics().getSecondLevelCacheHitCount());
			assertEquals(2, sessionFactory().getStatistics().getNaturalIdCacheHitCount());
		} );
	}
