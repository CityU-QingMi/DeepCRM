	private void testIt(Map config) {
		EntityManagerFactoryBuilder entityManagerFactoryBuilder = Bootstrap.getEntityManagerFactoryBuilder(
				new BaseEntityManagerFunctionalTestCase.TestingPersistenceUnitDescriptorImpl( getClass().getSimpleName() ),
				config
		);
		SessionFactoryImplementor sf = entityManagerFactoryBuilder.build().unwrap( SessionFactoryImplementor.class );
		final EntityPersister persister = sf.getMetamodel().entityPersister( AnEntity.class.getName() );

		try {
			if ( config.get( AvailableSettings.USE_SECOND_LEVEL_CACHE ).equals( "true" ) ) {
				assertNotNull( persister.getCacheAccessStrategy() );
			}
			else {
				assertNull( persister.getCacheAccessStrategy() );
			}
		}
		finally {
			sf.close();
		}
	}
