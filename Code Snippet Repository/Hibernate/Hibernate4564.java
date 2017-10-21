	private Connection acquireConnectionIfNeeded() {
		if ( physicalConnection == null ) {
			// todo : is this the right place for these observer calls?
			observer.jdbcConnectionAcquisitionStart();
			try {
				physicalConnection = jdbcConnectionAccess.obtainConnection();
			}
			catch (SQLException e) {
				throw sqlExceptionHelper.convert( e, "Unable to acquire JDBC Connection" );
			}
			finally {
				observer.jdbcConnectionAcquisitionEnd( physicalConnection );
			}
		}
		return physicalConnection;
	}
