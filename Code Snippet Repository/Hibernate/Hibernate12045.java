	protected void rebuildSessionFactory(Consumer<Configuration> configurationAdapter) {
		if ( sessionFactory == null ) {
			return;
		}
		try {
			sessionFactory.close();
			sessionFactory = null;
			configuration = null;
			serviceRegistry.destroy();
			serviceRegistry = null;
		}
		catch (Exception ignore) {
		}

		buildSessionFactory( configurationAdapter );
	}
