	@Before
	@SuppressWarnings( {""})
	public void buildEntityManagerFactory() throws Exception {
		log.trace( "Building EntityManagerFactory" );

		entityManagerFactory =  Bootstrap.getEntityManagerFactoryBuilder(
				buildPersistenceUnitDescriptor(),
				buildSettings()
		).build().unwrap( SessionFactoryImplementor.class );

		serviceRegistry = (StandardServiceRegistryImpl) entityManagerFactory.getServiceRegistry()
				.getParentServiceRegistry();

		afterEntityManagerFactoryBuilt();
	}
