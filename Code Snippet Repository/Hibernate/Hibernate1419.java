	public Integer getTimeout(String queryName) {
		Integer timeout = getInteger( queryName, QueryHints.TIMEOUT_JPA );

		if ( timeout != null ) {
			// convert milliseconds to seconds
			timeout = (int) Math.round( timeout.doubleValue() / 1000.0 );
		}
		else {
			// timeout is already in seconds
			timeout = getInteger( queryName, QueryHints.TIMEOUT_HIBERNATE );
		}
		return timeout;
	}
