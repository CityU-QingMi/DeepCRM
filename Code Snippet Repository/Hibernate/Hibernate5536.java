	@Test
	public void testInjectionAvailabilityFromEmf() {
		EntityManagerFactoryBuilder emfb = Bootstrap.getEntityManagerFactoryBuilder(
				new OrmVersionTest.PersistenceUnitInfoImpl( "my-test" ) {
					@Override
					public URL getPersistenceUnitRootUrl() {
						// just get any known url...
						return HibernatePersistenceProvider.class.getResource( "/org/hibernate/jpa/persistence_1_0.xsd" );
					}
				},
				Collections.emptyMap()
		);
			emfb.withValidatorFactory( vf );

			EntityManagerFactory emf = emfb.build();
		try {
			assertSame( vf, emf.getProperties().get( AvailableSettings.VALIDATION_FACTORY ) );
		}
		finally {
			emf.close();
		}
	}
