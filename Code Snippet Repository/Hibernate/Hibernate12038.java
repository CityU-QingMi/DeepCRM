	private void delist(Connection connection) {
		// todo : verify the incoming connection is the currently enlisted one?
		try {
			TestingJtaPlatformImpl.synchronizationRegistry().putResource( CONNECTION_KEY, null );
		}
		catch ( Exception e ) {
			System.err.println( "!!!Error trying to reset synchronization registry!!!" );
		}
		try {
			delegate.closeConnection( connection );
		}
		catch (SQLException e) {
			System.err.println( "!!!Error trying to close JDBC connection from delist callbacks!!!" );
		}
	}
