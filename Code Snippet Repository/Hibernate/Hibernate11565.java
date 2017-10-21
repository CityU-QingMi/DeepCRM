	private void addEntityCheckCache(SessionFactoryImplementor sessionFactory) throws Exception {
		Item item = new Item( "chris", "Chris's Item" );
		withTxSession(s -> s.persist( item ));

		withTxSession(s -> {
			Item found = s.load(Item.class, item.getId());
			Statistics stats = sessionFactory.getStatistics();
			log.info(stats.toString());
			assertEquals(item.getDescription(), found.getDescription());
			assertEquals(0, stats.getSecondLevelCacheMissCount());
			assertEquals(1, stats.getSecondLevelCacheHitCount());
			s.delete(found);
		});
	}
