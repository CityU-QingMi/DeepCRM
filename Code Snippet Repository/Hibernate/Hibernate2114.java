	protected void removeConnections(int numberToBeRemoved) {
		for ( int i = 0; i < numberToBeRemoved; i++ ) {
			Connection connection = availableConnections.poll();
			try {
				if ( connection != null ) {
					connection.close();
				}
				allConnections.remove( connection );
			}
			catch (SQLException e) {
				log.unableToCloseConnection( e );
			}
		}
	}
