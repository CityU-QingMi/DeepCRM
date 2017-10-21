	@Test
	public void testVersionNotIncrementedOnModificationOfNonOwningCollectionNonCascaded() {
		Session session = openSession();
		session.beginTransaction();
		Customer customer = new Customer();
		customer.id = 1L;
		session.save( customer );
		session.getTransaction().commit();
		session.close();

		long initial = customer.version;

		session = openSession();
		session.beginTransaction();
		customer = (Customer) session.get( Customer.class, 1L );
		assertEquals( initial, customer.version );
		Order order = new Order();
		order.id = 1L;
		order.customer = customer;
		customer.orders.add( order );
		session.save( order );
		session.getTransaction().commit();
		session.close();

		assertEquals( initial, customer.version );

		session = openSession();
		session.beginTransaction();
		customer = (Customer) session.get( Customer.class, 1L );
		assertEquals( initial, customer.version );
		Order order2 = new Order();
		order2.id = 2L;
		order2.customer = customer;
		customer.orders.add( order2 );
		session.save( order2 );
		session.getTransaction().commit();
		session.close();

		assertEquals( initial, customer.version );

		session = openSession();
		session.beginTransaction();
		customer = (Customer) session.load( Customer.class, 1L );
		assertEquals( initial, customer.version );
		session.delete( customer );
		session.getTransaction().commit();
		session.close();
	}
