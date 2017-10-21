	@Test
	public void testAddNewManyToManyPropertyRefNoInitFlushInitLeaveCacheConsistent() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();
		SecondLevelCacheStatistics cStats = stats.getSecondLevelCacheStatistics( Item.class.getName() + ".items" );

		ByRef<Long> otherItemId = new ByRef<>(null);
		withTxSession(s -> {
			OtherItem otherItem = new OtherItem();
			otherItem.setName( "steve" );
			s.save( otherItem );
			otherItemId.set(otherItem.getId());
		});

		// create an element for otherItem.bagOfItems
		Item item = new Item();
		item.setName( "element" );
		item.setDescription( "element Item" );

		withTxSession(s -> {
			OtherItem otherItem = s.get( OtherItem.class, otherItemId.get() );
			assertFalse( Hibernate.isInitialized( otherItem.getBagOfItems() ) );
			// Add an element to otherItem.bagOfItems (a bag); it will not initialize the bag.
			otherItem.addItemToBag( item );
			assertFalse( Hibernate.isInitialized( otherItem.getBagOfItems() ) );
			s.persist( item );
			s.flush();
			// Now initialize the collection; it will contain the uncommitted itemElement.
			// The many-to-many uses a property-ref
			Hibernate.initialize( otherItem.getBagOfItems() );
			markRollbackOnly(s);
		});

		withTxSession(s -> {
			OtherItem otherItem = s.get( OtherItem.class, otherItemId.get() );
			// Because of HHH-9231, the following will fail due to ObjectNotFoundException because the
			// collection will be read from the cache and it still contains the uncommitted element,
			// which cannot be found.
			Hibernate.initialize( otherItem.getBagOfItems() );
			assertTrue( otherItem.getBagOfItems().isEmpty() );
			s.delete( otherItem );
		});
	}
