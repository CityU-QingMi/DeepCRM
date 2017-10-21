	@Test
	public void testQueryCacheHitInSameTransaction() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();

		Item item = new Item( "galder", "Galder's Item" );

		withTxSession(s -> s.persist( item ));

		// Delay added to guarantee that query cache results won't be considered
		// as not up to date due to persist session and query results from first
		// query happening simultaneously.
		TIME_SERVICE.advance(1);

		withTxSession(s -> {
			s.createQuery("from Item").setCacheable(true).list();
			s.createQuery("from Item").setCacheable(true).list();
			assertEquals(1, stats.getQueryCacheHitCount());
		});

		withTxSession(s -> s.createQuery( "delete from Item" ).executeUpdate());
	}
