	@Test
	public void testManyToMany() {
		Application application = new Application();
		persist( application );
		Customer customer = new Customer();
		customer.applications.add( application );
		application.customers.add( customer );
		persist( customer );

		//init cache
		assertFalse( isCached( customer.id, Customer.class, "applications" ) );
		assertFalse( isCached( application.id, Application.class, "customers" ) );

		customer = find( Customer.class, customer.id );
		assertEquals( 1, customer.applications.size() );
		application = find( Application.class, application.id );
		assertEquals( 1, application.customers.size() );

		assertTrue( isCached( customer.id, Customer.class, "applications" ) );
		assertTrue( isCached( application.id, Application.class, "customers" ) );

		//read from cache
		customer = find( Customer.class, customer.id );
		assertEquals( 1, customer.applications.size() );
		application = find( Application.class, application.id );
		assertEquals( 1, application.customers.size() );
	}
