	@Test
	public void testCascade() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Discount discount = new Discount();
		discount.setDiscount( 20.12 );
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
		assertEquals( 20.12, discount.getDiscount(), 0.01 );
		assertNotNull( discount.getOwner() );
		customer = new Customer();
		customer.setName( "Clooney" );
		discount.setOwner( customer );
		discounts = new ArrayList();
		discounts.add( discount );
		customer.setDiscountTickets( discounts );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		discount = (Discount) s.get( Discount.class, discount.getId() );
		assertNotNull( discount );
		assertNotNull( discount.getOwner() );
		assertEquals( "Clooney", discount.getOwner().getName() );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		customer = (Customer) s.get( Customer.class, customer.getId() );
		s.delete( customer );
		tx.commit();
		s.close();
	}
