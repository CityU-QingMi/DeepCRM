	private void verifyConnections() {
		assertTrue( connectionProvider.getAcquiredConnections().isEmpty() );

		List<Connection> connections = connectionProvider.getReleasedConnections();
		assertEquals( 1, connections.size() );
		Connection connection = connections.get( 0 );
		try {
			verify(connection, never()).setAutoCommit( false );
		}
		catch (SQLException e) {
			fail(e.getMessage());
		}
	}
