	@Override
	public void stop() {
		if ( !active ) {
			return;
		}

		log.cleaningUpConnectionPool( pool.getUrl() );

		active = false;

		if ( executorService != null ) {
			executorService.shutdown();
		}
		executorService = null;

		try {
			pool.close();
		}
		catch (SQLException e) {
			log.unableToClosePooledConnection( e );
		}
	}
