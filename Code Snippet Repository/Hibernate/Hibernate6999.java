	private void buildSessionFactory(Class<?> entity) {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();

		try {
			new MetadataSources( serviceRegistry )
					.addAnnotatedClass( entity )
					.buildMetadata()
					.buildSessionFactory()
					.close();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
