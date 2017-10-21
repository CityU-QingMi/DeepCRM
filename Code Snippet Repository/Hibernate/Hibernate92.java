	@Test
	public void testLifecycle() {

		doInJPA( this::entityManagerFactory, entityManager -> {
			Customer customer = new Customer();
			customer.setId( 1L );
			customer.setFirstName( "John" );
			customer.setLastName( "Doe" );

			entityManager.persist( customer );
		} );

		EntityManagerFactory entityManagerFactory = null;
		try {
			Map settings = buildSettings();
			settings.put(
				org.hibernate.jpa.AvailableSettings.LOADED_CLASSES,
				Arrays.asList(
					ApplicationCustomer.class,
					CustomTrackingRevisionEntity.class
				)
			);
			settings.put(
					AvailableSettings.HBM2DDL_AUTO,
					"update"
			);
			entityManagerFactory =  Bootstrap
			.getEntityManagerFactoryBuilder(
				new TestingPersistenceUnitDescriptorImpl( getClass().getSimpleName() ),
				settings )
			.build()
			.unwrap( SessionFactoryImplementor.class );

			final EntityManagerFactory emf = entityManagerFactory;

			doInJPA( () -> emf, entityManager -> {
				ApplicationCustomer customer = new ApplicationCustomer();
				customer.setId( 2L );
				customer.setFirstName( "John" );
				customer.setLastName( "Doe Jr." );

				entityManager.persist( customer );
			} );
		}
		finally {
			if ( entityManagerFactory != null ) {
				entityManagerFactory.close();
			}
		}
	}
