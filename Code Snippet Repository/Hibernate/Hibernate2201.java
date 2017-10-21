	@SuppressWarnings({""})
	public void handleAndClearWarnings(
			Connection connection,
			WarningHandler handler) {
		try {
			if ( logWarnings ) {
				walkWarnings( connection.getWarnings(), handler );
			}
		}
		catch (SQLException sqle) {
			// workaround for WebLogic
			LOG.debug( "could not log warnings", sqle );
		}
		try {
			// Sybase fail if we don't do that, sigh...
			connection.clearWarnings();
		}
		catch (SQLException sqle) {
			LOG.debug( "could not clear warnings", sqle );
		}
	}
