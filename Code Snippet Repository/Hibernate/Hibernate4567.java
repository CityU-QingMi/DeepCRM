	private void releaseConnection() {
		if ( physicalConnection == null ) {
			return;
		}

		// todo : is this the right place for these observer calls?
		observer.jdbcConnectionReleaseStart();
		try {
			if ( !physicalConnection.isClosed() ) {
				sqlExceptionHelper.logAndClearWarnings( physicalConnection );
			}
			jdbcConnectionAccess.releaseConnection( physicalConnection );
		}
		catch (SQLException e) {
			throw sqlExceptionHelper.convert( e, "Unable to release JDBC Connection" );
		}
		finally {
			observer.jdbcConnectionReleaseEnd();
			physicalConnection = null;
			getResourceRegistry().releaseResources();
		}
	}
