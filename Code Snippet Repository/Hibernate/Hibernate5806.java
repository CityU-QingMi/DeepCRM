	@Test
	@SuppressWarnings("")
	public void testIdentifierGeneratorStrategyProvider() {
		Map settings = new HashMap();
		settings.put(
				AvailableSettings.IDENTIFIER_GENERATOR_STRATEGY_PROVIDER,
				FunkyIdentifierGeneratorProvider.class.getName()
		);
		settings.put( AvailableSettings.LOADED_CLASSES, Collections.singletonList( Cable.class ) );

		final EntityManagerFactory entityManagerFactory = Bootstrap.getEntityManagerFactoryBuilder(
				new PersistenceUnitInfoAdapter(),
				settings
		).build();

		final EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
			entityManager.persist( new Cable() );
			entityManager.flush();
            Assert.fail( "FunkyException should have been thrown when the id is generated" );
        }
        catch ( FunkyException e ) {
			entityManager.close();
            entityManagerFactory.close();
        }
    }
