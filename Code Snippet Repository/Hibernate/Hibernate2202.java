	@SuppressWarnings({""})
	public void handleAndClearWarnings(
			Statement statement,
			WarningHandler handler) {
		// See HHH-9174.  Statement#getWarnings can be an expensive call for many JDBC libs.  Don't do it unless
		// the log level would actually allow a warning to be logged.
		if ( logWarnings ) {
			try {
				walkWarnings( statement.getWarnings(), handler );
			}
			catch (SQLException sqlException) {
				// workaround for WebLogic
				LOG.debug( "could not log warnings", sqlException );
			}
		}
		try {
			// Sybase fail if we don't do that, sigh...
			statement.clearWarnings();
		}
		catch (SQLException sqle) {
			LOG.debug( "could not clear warnings", sqle );
		}
	}
