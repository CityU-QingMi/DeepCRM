	private void postNext() throws SQLException {
		LOG.debug( "Attempting to retrieve next results" );
		this.hasNext = rs.next();
		if ( !hasNext ) {
			LOG.debug( "Exhausted results" );
			close();
		}
		else {
			LOG.debug( "Retrieved next results" );
		}
	}
