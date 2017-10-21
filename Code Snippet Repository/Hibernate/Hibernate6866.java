	@Test
	public void testSimpleOneToManyCollection() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Discount d = new Discount();
		d.setDiscount( 10 );
		Customer c = new Customer();
		List discounts = new ArrayList();
		discounts.add( d );
		d.setOwner( c );
		c.setDiscountTickets( discounts );
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		c = ( Customer ) s.load( Customer.class, c.getId() );
		assertNotNull( c );
		assertFalse( Hibernate.isInitialized( c.getDiscountTickets() ) );
		assertNotNull( c.getDiscountTickets() );
		Collection collecDiscount = c.getDiscountTickets();
		assertTrue( collecDiscount.size() > 0 );
		tx.commit();
		s.close();
	}
