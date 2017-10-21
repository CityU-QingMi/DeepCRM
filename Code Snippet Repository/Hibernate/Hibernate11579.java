	@Test
	@TestForIssue( jiraKey = "" )
	public void testAddNewOneToManyElementInitFlushLeaveCacheConsistent() throws Exception {
		Statistics stats = sessionFactory().getStatistics();
		stats.clear();
		SecondLevelCacheStatistics cStats = stats.getSecondLevelCacheStatistics( Item.class.getName() + ".items" );

		ByRef<Long> itemId = new ByRef<>(null);
		saveItem(itemId);

		// create an element for item.itsms
		Item itemElement = new Item();
		itemElement.setName( "element" );
		itemElement.setDescription( "element item" );

		withTxSession(s -> {
			Item item = s.get( Item.class, itemId.get() );
			assertFalse( Hibernate.isInitialized( item.getItems() ) );
			// Add an element to item.items (a Set); it will initialize the Set.
			item.addItem( itemElement );
			assertTrue( Hibernate.isInitialized( item.getItems() ) );
			s.persist( itemElement );
			s.flush();
			markRollbackOnly(s);
		});

		withTxSession(s -> {
			Item item = s.get( Item.class, itemId.get() );
			Hibernate.initialize( item.getItems() );
			assertTrue( item.getItems().isEmpty() );
			s.delete( item );
		});
	}
