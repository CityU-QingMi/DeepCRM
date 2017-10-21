	public void release() throws SQLException {
		// we only release the connection
		if ( connection != null ) {
			sqlExceptionHelper.logAndClearWarnings( connection );
			if ( toggleAutoCommit ) {
				connection.setAutoCommit( false );
			}
			provider.closeConnection( connection );
			connection = null;
		}
	}
