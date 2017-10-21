	@Test
	@SuppressWarnings("")
	public void testPersisterClassProvider() {
		Map settings = SettingsGenerator.generateSettings(
				PersisterClassResolverInitiator.IMPL_NAME, GoofyPersisterClassProvider.class,
				AvailableSettings.LOADED_CLASSES, Arrays.asList( Bell.class )
		);
		try {
			EntityManagerFactory entityManagerFactory = Bootstrap.getEntityManagerFactoryBuilder(
					new PersistenceUnitDescriptorAdapter(),
					settings
			).build();
			entityManagerFactory.close();
		}
		catch ( PersistenceException e ) {
            Assert.assertNotNull( e.getCause() );
			Assert.assertNotNull( e.getCause().getCause() );
			Assert.assertEquals( GoofyException.class, e.getCause().getCause().getClass() );

		}
	}
