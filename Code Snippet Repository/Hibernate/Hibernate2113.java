	public void close() throws SQLException {
		try {
			int allocationCount = allConnections.size() - availableConnections.size();
			if(allocationCount > 0) {
				log.error( "Connection leak detected: there are " + allocationCount + " unclosed connections upon shutting down pool " + getUrl());
			}
		}
		finally {
			for ( Connection connection : allConnections ) {
				connection.close();
			}
		}
	}
