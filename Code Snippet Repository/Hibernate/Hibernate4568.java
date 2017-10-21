	@Override
	public Connection close() {
		if ( closed ) {
			return null;
		}

		getResourceRegistry().releaseResources();

		log.trace( "Closing logical connection" );
		try {
			releaseConnection();
		}
		finally {
			// no matter what
			closed = true;
			log.trace( "Logical connection closed" );
		}
		return null;
	}
