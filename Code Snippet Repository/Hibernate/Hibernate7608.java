	@Before
	public void init() {
		final Session session = openSession();
		session.beginTransaction();

		for ( int i = 0; i < 50; i++ ) {
			Product product = new Product();
			product.name = "Product " + i % 10;
			session.persist( product );
		}

		Truck truck1 = new Truck();
		Truck truck2 = new Truck();
		SportsCar sportsCar1 = new SportsCar();
		session.persist( truck1 );
		session.persist( truck2 );
		session.persist( sportsCar1 );

		session.getTransaction().commit();
		session.close();
	}
