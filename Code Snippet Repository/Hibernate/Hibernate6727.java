	private void tryBuildingSessionFactory(Class... annotatedClasses) {
		final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().build();
		try {
			final MetadataSources metadataSources = new MetadataSources( serviceRegistry );
			for ( Class annotatedClass : annotatedClasses ) {
				metadataSources.addAnnotatedClass( annotatedClass );
			}

			final Metadata metadata = metadataSources.buildMetadata();
			final SessionFactory sessionFactory = metadata.buildSessionFactory();
			sessionFactory.close();
		}
		finally {
			StandardServiceRegistryBuilder.destroy( serviceRegistry );
		}
	}
