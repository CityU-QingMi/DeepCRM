	@Test
	public void testManyToManyPersist() {
		//if an error happen, it will propagate the exception failing the test case
		Session s = openSession();
		s.beginTransaction();

		Application application = new Application();
		s.save( application );

		Customer customer = new Customer();
		customer.applications.add( application );
		s.save( customer );

		s.getTransaction().commit();
		s.close();


		s = openSession();

		assertEquals( 1, s.get( Application.class, application.id ).customers.size() );
		assertEquals( 1, s.get( Customer.class, customer.id ).applications.size() );

		s.close();

		s = openSession();
		s.beginTransaction();

		Customer customer2 = new Customer();
		customer2.applications.add( application );
		s.save( customer2 );

		s.getTransaction().commit();
		s.close();
	}
