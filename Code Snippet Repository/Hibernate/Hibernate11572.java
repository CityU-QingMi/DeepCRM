	@Test
	public void testCollectionCache() throws Exception {
		final Statistics stats = sessionFactory().getStatistics();
		stats.clear();

		final Item item = new Item( "chris", "Chris's Item" );
		final Item another = new Item( "another", "Owned Item" );
		item.addItem( another );

		withTxSession(s -> {
			s.persist( item );
			s.persist( another );
		});
		// The collection has been removed, but we can't add it again immediately using putFromLoad
		TIME_SERVICE.advance(1);

		withTxSession(s -> {
			Item loaded = s.load( Item.class, item.getId() );
			assertEquals( 1, loaded.getItems().size() );
		});

		SecondLevelCacheStatistics cStats = stats.getSecondLevelCacheStatistics( Item.class.getName() + ".items" );
		assertEquals( 1, cStats.getElementCountInMemory() );

		withTxSession(s -> {
			Item loadedWithCachedCollection = (Item) s.load( Item.class, item.getId() );
			stats.logSummary();
			assertEquals( item.getName(), loadedWithCachedCollection.getName() );
			assertEquals( item.getItems().size(), loadedWithCachedCollection.getItems().size() );
			assertEquals( 1, cStats.getHitCount() );
			Map cacheEntries = cStats.getEntries();
			assertEquals( 1, cacheEntries.size() );
			Item itemElement = loadedWithCachedCollection.getItems().iterator().next();
			itemElement.setOwner( null );
			loadedWithCachedCollection.getItems().clear();
			s.delete( itemElement );
			s.delete( loadedWithCachedCollection );
		});
	}
