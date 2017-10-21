	@Before
	public void prepareTest() throws Exception {
		doInJPA( this::entityManagerFactory, entityManager -> {

			final Item item1 = new Item( "first" );
			entityManager.persist( item1 );

			final Item item2 = new Item( "second" );
			entityManager.persist( item2 );

			final ItemRelation rel = new ItemRelation();
			item1.addLowerItemRelations( rel );
			item2.addHigherItemRelations( rel );

			entityManager.persist( rel );
		} );
	}
