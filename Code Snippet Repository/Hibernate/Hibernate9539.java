	private SessionFactory buildSessionFactory() {
		Map settings = new HashMap();
		settings.putAll( Environment.getProperties() );
		TestingJtaBootstrap.prepare( settings );
		settings.put( AvailableSettings.TRANSACTION_COORDINATOR_STRATEGY, "jta" );

		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().applySettings( settings ).build();

		return new MetadataSources( ssr )
				.addAnnotatedClass( TestEntity.class )
				.buildMetadata()
				.buildSessionFactory();
	}
