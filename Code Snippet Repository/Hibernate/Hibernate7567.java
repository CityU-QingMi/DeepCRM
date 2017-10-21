	private Seller[] persist(String prefix) {
		Session session = openSession();

		Transaction tx = session.beginTransaction();
		Seller seller1 = new Seller( prefix + "-seller1" );
		Seller seller2 = new Seller( prefix + "-seller2" );

		Customer customer1 = new Customer( prefix + "-customer1" );
		Customer customer2 = new Customer( prefix + "-customer2" );
		Customer customer3 = new Customer( prefix + "-customer3" );

		seller1.addCustomer( customer1 );
		seller1.addCustomer( customer2 );
		seller2.addCustomer( customer2 );
		seller2.addCustomer( customer3 );

		session.persist( customer1 );
		session.persist( customer2 );
		session.persist( customer3 );
		session.persist( seller1 );
		session.persist( seller2 );

		tx.commit();
		session.close();
		return new Seller[] {seller1, seller2};
	}
