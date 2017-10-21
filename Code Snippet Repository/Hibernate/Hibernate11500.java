	private SessionFactoryImplementor getSessionFactory(String cacheKeysFactory) {
		Configuration configuration = new Configuration()
				.setProperty( Environment.USE_SECOND_LEVEL_CACHE, "true")
				.setProperty(Environment.CACHE_REGION_FACTORY, TestInfinispanRegionFactory.class.getName())
				.setProperty(Environment.DEFAULT_CACHE_CONCURRENCY_STRATEGY, "transactional")
				.setProperty( AvailableSettings.SHARED_CACHE_MODE, "ALL")
				.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
		if (cacheKeysFactory != null) {
			configuration.setProperty(Environment.CACHE_KEYS_FACTORY, cacheKeysFactory);
		}
		configuration.addAnnotatedClass( WithSimpleId.class );
		configuration.addAnnotatedClass( WithEmbeddedId.class );
		return (SessionFactoryImplementor) configuration.buildSessionFactory();
	}
