	@Test
	public void hibernateInitialize() {
		Customer customer = new Customer();
		Item item1 = new Item( customer );
		Item item2 = new Item( customer );
		customer.boughtItems.add( item1 );
		customer.boughtItems.add( item2 );
		persist( customer );

		customer = find( Customer.class, customer.id );
		assertFalse( Hibernate.isInitialized( customer.boughtItems ) );
		Hibernate.initialize( customer.boughtItems );
		assertTrue( Hibernate.isInitialized( customer.boughtItems ) );
	}
