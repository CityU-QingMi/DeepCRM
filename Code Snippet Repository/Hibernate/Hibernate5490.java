    @Test
	public void testJdbc3LobCreator() throws SQLException {
		final Connection connection = createConnectionProxy( 3, new JdbcLobBuilderImpl( false) );
		LobCreationContext lobCreationContext = new LobCreationContextImpl( connection );

		LobCreator lobCreator =
				new LobCreatorBuilder( new Properties(), connection )
						.buildLobCreator( lobCreationContext );
		assertSame( NonContextualLobCreator.INSTANCE, lobCreator );

		testLobCreation( lobCreator );
		connection.close();
	}
