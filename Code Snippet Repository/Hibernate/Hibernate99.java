	@Test(expected = SecurityException.class)
	public void testLoadListener() {
		Serializable customerId = 1L;

		doInJPA( this::entityManagerFactory, entityManager -> {
			//tag::events-interceptors-load-listener-example[]
			EntityManagerFactory entityManagerFactory = entityManagerFactory();
			SessionFactoryImplementor sessionFactory = entityManagerFactory.unwrap( SessionFactoryImplementor.class );
			sessionFactory
				.getServiceRegistry()
				.getService( EventListenerRegistry.class )
				.prependListeners( EventType.LOAD, new SecuredLoadEntityListener() );

			Customer customer = entityManager.find( Customer.class, customerId );
			//end::events-interceptors-load-listener-example[]
		} );
	}
