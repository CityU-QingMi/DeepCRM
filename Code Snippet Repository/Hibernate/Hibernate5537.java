	@Test
	public void basicNewBootProcessTest() {
		Map settings = new HashMap();

		HibernatePersistenceProvider persistenceProvider = new HibernatePersistenceProvider();
		final EntityManagerFactory emf = persistenceProvider.createContainerEntityManagerFactory(
				new OrmVersionTest.PersistenceUnitInfoImpl( "my-test" ) {
					@Override
					public URL getPersistenceUnitRootUrl() {
						// just get any known url...
						return HibernatePersistenceProvider.class.getResource( "/org/hibernate/jpa/persistence_1_0.xsd" );
					}
				},
				settings
		);
		emf.close();
	}
