    @Test
	public void testSessionFactoryObserverProperty() {

		EntityManagerFactoryBuilder builder = Bootstrap.getEntityManagerFactoryBuilder(
				new PersistenceUnitInfoAdapter(),
				Collections.singletonMap(
						AvailableSettings.SESSION_FACTORY_OBSERVER,
						GoofySessionFactoryObserver.class.getName()
				)
		);

		try {
			final EntityManagerFactory entityManagerFactory = builder.build();
			entityManagerFactory.close();
			Assert.fail( "GoofyException should have been thrown" );
		}
		catch ( GoofyException e ) {
			//success
		}
	}
