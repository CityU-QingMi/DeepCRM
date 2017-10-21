	@Test
	@TestForIssue( jiraKey = "")
	public void testPersistEntityFlushEvictGetRollbackNotInEntityCache() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();
		SecondLevelCacheStatistics slcs = stats.getSecondLevelCacheStatistics( Item.class.getName() );

		ByRef<Long> itemId = new ByRef<>(null);
		withTxSession(s -> {
			Item item = new Item();
			item.setName("steve");
			item.setDescription("steve's item");
			s.persist(item);
			s.flush();
			itemId.set(item.getId());
			// item is cached on insert.
//			assertNotNull( slcs.getEntries().get( item.getId() ) );
			s.evict(item);
			assertEquals(slcs.getHitCount(), 0);
			item = s.get(Item.class, item.getId());
			assertNotNull(item);
//			assertEquals( slcs.getHitCount(), 1 );
//			assertNotNull( slcs.getEntries().get( item.getId() ) );
			markRollbackOnly(s);
		});

		// item should not be in entity cache.
		//slcs = stats.getSecondLevelCacheStatistics( Item.class.getName() );
		assertEquals(Collections.EMPTY_MAP, slcs.getEntries() );

		withTxSession(s -> {
			Item item = s.get(Item.class, itemId.get());
			assertNull(item);
		});
	}
