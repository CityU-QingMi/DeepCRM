	public void testBootstrapWithClassMappedMOreThanOnce() {
		Map settings = new HashMap(  );
		settings.put( AvailableSettings.HBXML_FILES, "org/hibernate/jpa/test/callbacks/hbmxml/ClassMappedMoreThanOnce.hbm.xml" );

		final EntityManagerFactoryBuilder builder = Bootstrap.getEntityManagerFactoryBuilder(
				new BaseEntityManagerFunctionalTestCase.TestingPersistenceUnitDescriptorImpl( getClass().getSimpleName() ),
				settings
		);

		HibernateEntityManagerFactory emf = null;
		try {
			emf = builder.build().unwrap( HibernateEntityManagerFactory.class );
		}
		finally {
			if ( emf != null ) {
				try {
					emf.close();
				}
				catch (Exception ignore) {
				}
			}
		}
	}
