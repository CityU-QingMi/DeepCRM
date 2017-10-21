	@Test
	public void testConnectedLobCreator() throws SQLException {
		final Connection connection = createConnectionProxy( 4, new JdbcLobBuilderImpl( true ) );
		LobCreationContext lobCreationContext = new LobCreationContextImpl( connection );

		LobCreator lobCreator =
				new LobCreatorBuilder( new Properties(), connection )
						.buildLobCreator( lobCreationContext );
		assertTrue( lobCreator instanceof ContextualLobCreator );
		testLobCreation( lobCreator );

		connection.close();
	}
