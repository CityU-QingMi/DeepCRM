	public void logExceptions(SQLException sqlException, String message) {
		if ( LOG.isEnabled( Level.ERROR ) ) {
			if ( LOG.isDebugEnabled() ) {
				message = StringHelper.isNotEmpty( message ) ? message : DEFAULT_EXCEPTION_MSG;
				LOG.debug( message, sqlException );
			}
			final boolean warnEnabled = LOG.isEnabled( Level.WARN );
			while ( sqlException != null ) {
				if ( warnEnabled ) {
					LOG.warn( "SQL Error: " + sqlException.getErrorCode() + ", SQLState: " + sqlException.getSQLState() );
				}
				LOG.error( sqlException.getMessage() );
				sqlException = sqlException.getNextException();
			}
		}
	}
