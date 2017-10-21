	@Override
	public void registerRefCursorParameter(CallableStatement statement, int position) {
		if ( jdbcServices.getExtractedMetaDataSupport().supportsRefCursors() ) {
			try {
				statement.registerOutParameter( position, refCursorTypeCode() );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert( e, "Error registering REF_CURSOR parameter [" + position + "]" );
			}
		}
		else {
			try {
				jdbcServices.getDialect().registerResultSetOutParameter( statement, position );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert( e, "Error asking dialect to register ref cursor parameter [" + position + "]" );
			}
		}
	}
