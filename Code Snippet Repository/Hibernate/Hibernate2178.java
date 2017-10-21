	@Override
	public Connection close() {
		LOG.tracev( "Closing JDBC container [{0}]", this );
		Connection connection;
		try {
			if ( currentBatch != null ) {
				LOG.closingUnreleasedBatch();
				currentBatch.release();
			}
			cleanup();
		}
		finally {
			connection = logicalConnection.close();
		}
		return connection;
	}
