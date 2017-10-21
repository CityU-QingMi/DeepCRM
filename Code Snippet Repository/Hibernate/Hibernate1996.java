	@Override
	public void jdbcExecuteBatchStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcExecuteBatchStart();
		}
	}
