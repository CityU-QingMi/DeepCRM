	@Before
	public void startUp() {
		// create the EMF
		entityManagerFactory = Bootstrap.getEntityManagerFactoryBuilder(
				buildPersistenceUnitDescriptor(),
				buildSettingsMap()
		).build().unwrap( HibernateEntityManagerFactory.class );

		// create the procedures
		createTestData( entityManagerFactory );
		createProcedures( entityManagerFactory );
	}
