	@Test
	public void testOneToMany() {
		Customer customer = new Customer();
		Item item1 = new Item( customer );
		Item item2 = new Item( customer );
		customer.boughtItems.add( item1 );
		customer.boughtItems.add( item2 );
		persist( customer );

		//init cache
		assertFalse( isCached( customer.id, Customer.class, "boughtItems" ) );
		customer = find( Customer.class, customer.id );
		assertEquals( 2, customer.boughtItems.size() );

		//read from cache
		assertTrue( isCached( customer.id, Customer.class, "boughtItems" ) );
		customer = find( Customer.class, customer.id );
		assertEquals( 2, customer.boughtItems.size() );
	}
