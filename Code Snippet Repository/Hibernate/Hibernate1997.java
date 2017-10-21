	@Override
	public void jdbcExecuteBatchEnd() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcExecuteBatchEnd();
		}
	}
