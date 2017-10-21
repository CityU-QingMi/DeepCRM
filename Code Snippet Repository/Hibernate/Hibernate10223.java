	@BeforeClassOnce
	@SuppressWarnings({""})
	public void buildEntityManagerFactory() throws Exception {
		log.trace( "Building EntityManagerFactory" );

		entityManagerFactoryBuilder = (EntityManagerFactoryBuilderImpl) Bootstrap.getEntityManagerFactoryBuilder(
				buildPersistenceUnitDescriptor(),
				buildSettings()
		);
		entityManagerFactory = entityManagerFactoryBuilder.build().unwrap( SessionFactoryImplementor.class );

		serviceRegistry = (StandardServiceRegistryImpl) entityManagerFactory.getServiceRegistry()
				.getParentServiceRegistry();

		afterEntityManagerFactoryBuilt();
	}
