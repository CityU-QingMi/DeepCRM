	@Test
	public void testFetch() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Discount discount = new Discount();
		discount.setDiscount( 20 );
		Customer customer = new Customer();
		Collection discounts = new ArrayList();
		discounts.add( discount );
		customer.setName( "Quentin Tarantino" );
		discount.setOwner( customer );
		customer.setDiscountTickets( discounts );
		s.persist( discount );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		discount = (Discount) s.get( Discount.class, discount.getId() );
		assertNotNull( discount );
		assertFalse( Hibernate.isInitialized( discount.getOwner() ) );
		tx.commit();

		s = openSession();
		tx = s.beginTransaction();
		discount = (Discount) s.load( Discount.class, discount.getId() );
		assertNotNull( discount );
		assertFalse( Hibernate.isInitialized( discount.getOwner() ) );
		tx.commit();

		s = openSession();
		tx = s.beginTransaction();
		s.delete( s.get( Discount.class, discount.getId() ) );
		tx.commit();
		s.close();
	}
