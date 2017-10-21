	@Test
	public void testSettingIsolationAsNumeric() throws Exception {
		Properties properties = Environment.getProperties();
		augmentConfigurationSettings( properties );
		properties.put( AvailableSettings.ISOLATION, Connection.TRANSACTION_SERIALIZABLE );

		ConnectionProvider provider = getConnectionProviderUnderTest();

		try {
			( (Configurable) provider ).configure( properties );

			if ( Startable.class.isInstance( provider ) ) {
				( (Startable) provider ).start();
			}

			Connection connection = provider.getConnection();
			assertEquals( Connection.TRANSACTION_SERIALIZABLE, connection.getTransactionIsolation() );
			provider.closeConnection( connection );
		}
		finally {
			( (Stoppable) provider ).stop();
		}
	}
