	@Override
	public void jdbcConnectionReleaseStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcConnectionReleaseStart();
		}
	}
