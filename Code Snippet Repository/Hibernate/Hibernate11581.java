	@Test
	public void testAddNewOneToManyElementNoInitFlushInitLeaveCacheConsistent() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();
		SecondLevelCacheStatistics cStats = stats.getSecondLevelCacheStatistics( Item.class.getName() + ".items" );

		ByRef<Long> itemId = new ByRef<>(null);

		saveItem(itemId);

		// create an element for item.bagOfItems
		Item itemElement = new Item();
		itemElement.setName( "element" );
		itemElement.setDescription( "element item" );

		withTxSession(s -> {
			Item item = s.get(Item.class, itemId.get());
			assertFalse(Hibernate.isInitialized(item.getBagOfItems()));
			// Add an element to item.bagOfItems (a bag); it will not initialize the bag.
			item.addItemToBag(itemElement);
			assertFalse(Hibernate.isInitialized(item.getBagOfItems()));
			s.persist(itemElement);
			s.flush();
			// Now initialize the collection; it will contain the uncommitted itemElement.
			Hibernate.initialize(item.getBagOfItems());
			markRollbackOnly(s);
		});

		withTxSession(s -> {
			Item item = s.get(Item.class, itemId.get());
			// Because of HHH-9231, the following will fail due to ObjectNotFoundException because the
			// collection will be read from the cache and it still contains the uncommitted element,
			// which cannot be found.
			Hibernate.initialize(item.getBagOfItems());
			assertTrue(item.getBagOfItems().isEmpty());
			s.delete(item);
		});
	}
