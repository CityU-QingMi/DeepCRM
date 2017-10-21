	@Test
	public void testVersionNotIncrementedOnModificationOfNonOwningCollectionCascaded() {
		Customer customer = new Customer();
		customer.id = 1L;

		Session session = openSession();
		session.beginTransaction();
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
		session.getTransaction().commit();
		session.close();

		assertEquals( initial, customer.version );

		session = openSession();
		session.beginTransaction();
		customer = (Customer) session.get( Customer.class, 1L );
		Order order2 = new Order();
		order2.id = 2L;
		order2.customer = customer;
		customer.orders.add( order2 );
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
