	@Override
	public void closeConnection(Connection conn) throws SQLException {
		if ( conn == null ) {
			return;
		}

		if ( nonEnlistedConnections.contains( conn ) ) {
			nonEnlistedConnections.remove( conn );
			delegate.closeConnection( conn );
		}
		else {
			// do nothing.  part of the enlistment contract here is that the XAResource wrapper
			// takes that responsibility.
		}
	}
