	@Override
	public void export(String string) throws Exception {
		statement.executeUpdate( string );
		try {
			SQLWarning warnings = statement.getWarnings();
			if ( warnings != null) {
				sqlExceptionHelper.logAndClearWarnings( connection );
			}
		}
		catch( SQLException e ) {
			LOG.unableToLogSqlWarnings( e );
		}
	}
