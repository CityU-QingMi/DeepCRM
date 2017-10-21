	@Override
	public Connection close() {
		log.trace( "Closing logical connection" );

		getResourceRegistry().releaseResources();

		try {
			return providedConnection;
		}
		finally {
			providedConnection = null;
			closed = true;
			log.trace( "Logical connection closed" );
		}
	}
