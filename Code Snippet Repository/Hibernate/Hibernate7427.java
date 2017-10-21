	@Test
	public void testPassingConnectionProviderInstanceToBootstrap() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.CONNECTION_PROVIDER, TestingConnectionProviderImpl.INSTANCE )
				.build();
		try {
			assert ssr.getService( ConnectionProvider.class ) == TestingConnectionProviderImpl.INSTANCE;
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
