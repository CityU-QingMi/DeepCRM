	@Test
	public void testSessionFactoryInterceptor() {

		Serializable customerId = 1L;
		//tag::events-interceptors-session-factory-scope-example[]
		SessionFactory sessionFactory = new MetadataSources( new StandardServiceRegistryBuilder().build() )
			.addAnnotatedClass( Customer.class )
			.getMetadataBuilder()
			.build()
			.getSessionFactoryBuilder()
			.applyInterceptor( new LoggingInterceptor() )
			.build();
		//end::events-interceptors-session-factory-scope-example[]
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Customer customer = session.get( Customer.class, customerId );
		customer.setName( "Mr. John Doe" );
		//Entity Customer#1 changed from [John Doe, 0] to [Mr. John Doe, 0]
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
