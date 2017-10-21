	@Override
	public void registerRefCursorParameter(CallableStatement statement, String name) {
		if ( jdbcServices.getExtractedMetaDataSupport().supportsRefCursors() ) {
			try {
				statement.registerOutParameter( name, refCursorTypeCode() );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert( e, "Error registering REF_CURSOR parameter [" + name + "]" );
			}
		}
		else {
			try {
				jdbcServices.getDialect().registerResultSetOutParameter( statement, name );
			}
			catch (SQLException e) {
				throw jdbcServices.getSqlExceptionHelper().convert( e, "Error asking dialect to register ref cursor parameter [" + name + "]" );
			}
		}
	}
