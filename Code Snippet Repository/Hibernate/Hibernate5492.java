    @Test
	public void testConfiguredNonContextualLobCreator() throws SQLException {
		final Connection connection = createConnectionProxy( 4, new JdbcLobBuilderImpl( true ) );
		LobCreationContext lobCreationContext = new LobCreationContextImpl( connection );

		Properties props = new Properties();
		props.setProperty( Environment.NON_CONTEXTUAL_LOB_CREATION, "true" );
		LobCreator lobCreator =
				new LobCreatorBuilder( props, connection )
						.buildLobCreator( lobCreationContext );
		assertSame( NonContextualLobCreator.INSTANCE, lobCreator );

		testLobCreation( lobCreator );
		connection.close();
	}
