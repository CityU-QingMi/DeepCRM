	@After
	public void tearDown() {
		final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.enableAutoClose()
				.applySetting( AvailableSettings.HBM2DDL_AUTO, "drop" )
				.build();

		SessionFactoryImplementor sessionFactory = null;

		try {
			final Metadata metadata = new MetadataSources( serviceRegistry )
					.addAnnotatedClass( Customer.class )
					.buildMetadata();
			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();
		}
		finally {
			if ( sessionFactory != null ) {
				sessionFactory.close();
			}
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}

	}
