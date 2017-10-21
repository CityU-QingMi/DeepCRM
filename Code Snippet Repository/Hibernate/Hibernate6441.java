	@Test
	public void testComplexIdClass() {
		Session s = openSession();
		Transaction tx = s.beginTransaction();

		Customer c1 = new Customer(
				"foo", "bar", "contact1", "100", new BigDecimal( 1000 ), new BigDecimal( 1000 ), new BigDecimal( 1000 ));
		s.persist( c1 );
		s.flush();
        s.clear();
		
//      why does this cause a failure?        
//		Customer c2 = new Customer(
//              "foo1", "bar1", "contact2", "200", new BigDecimal( 2000 ), new BigDecimal( 2000 ), new BigDecimal( 2000 ));
//		s.persist( c2 );
//		s.flush();
//        s.clear();
		
		Item boat = new Item();
		boat.setId( "1" );
		boat.setName( "cruiser" );
		boat.setPrice( new BigDecimal( 500 ) );
		boat.setDescription( "a boat" );
		boat.setCategory( 42 );

		s.persist( boat );
		s.flush();
		s.clear();

		c1.addInventory( boat, 10, new BigDecimal( 5000 ) );
		s.merge( c1 );
		s.flush();
		s.clear();

		Customer c12 = ( Customer ) s.createQuery( "select c from Customer c" ).uniqueResult();

		List<CustomerInventory> inventory = c12.getInventories();

		assertEquals( 1, inventory.size() );
		assertEquals( 10, inventory.get( 0 ).getQuantity() );

		tx.rollback();
		s.close();

		assertTrue( true );
	}
