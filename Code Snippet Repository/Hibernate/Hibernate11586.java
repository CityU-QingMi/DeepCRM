	@Test
	public void testQueryCacheInvalidation() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();

		SecondLevelCacheStatistics slcs = stats.getSecondLevelCacheStatistics( Item.class.getName() );
		sessionFactory().getCache().evictEntityRegion( Item.class.getName() );

		TIME_SERVICE.advance(1);

		assertEquals(0, slcs.getPutCount());
		assertEquals( 0, slcs.getElementCountInMemory() );
		assertEquals( 0, slcs.getEntries().size() );

		ByRef<Long> idRef = new ByRef<>(null);
		withTxSession(s -> {
			Item item = new Item();
			item.setName( "widget" );
			item.setDescription( "A really top-quality, full-featured widget." );
			s.persist( item );
			idRef.set( item.getId() );
		});

		assertEquals( 1, slcs.getPutCount() );
		assertEquals( 1, slcs.getElementCountInMemory() );
		assertEquals( 1, slcs.getEntries().size() );

		withTxSession(s -> {
			Item item = s.get( Item.class, idRef.get() );
			assertEquals( slcs.getHitCount(), 1 );
			assertEquals( slcs.getMissCount(), 0 );
			item.setDescription( "A bog standard item" );
		});

		assertEquals( slcs.getPutCount(), 2 );

		CacheEntry entry = (CacheEntry) slcs.getEntries().get( idRef.get() );
		Serializable[] ser = entry.getDisassembledState();
		assertTrue( ser[0].equals( "widget" ) );
		assertTrue( ser[1].equals( "A bog standard item" ) );

		withTxSession(s -> {
			Item item = s.load(Item.class, idRef.get());
			s.delete(item);
		});
	}
