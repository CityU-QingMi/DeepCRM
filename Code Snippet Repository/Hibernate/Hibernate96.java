	@Test
	public void testSessionInterceptor() {
		EntityManagerFactory entityManagerFactory = entityManagerFactory();
		Serializable customerId = 1L;
		//tag::events-interceptors-session-scope-example[]
		SessionFactory sessionFactory = entityManagerFactory.unwrap( SessionFactory.class );
		Session session = sessionFactory
			.withOptions()
			.interceptor(new LoggingInterceptor() )
			.openSession();
		session.getTransaction().begin();

		Customer customer = session.get( Customer.class, customerId );
		customer.setName( "Mr. John Doe" );
		//Entity Customer#1 changed from [John Doe, 0] to [Mr. John Doe, 0]

		session.getTransaction().commit();
		//end::events-interceptors-session-scope-example[]
		session.close();
	}
