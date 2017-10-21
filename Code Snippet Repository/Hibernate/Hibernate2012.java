	@Override
	public void jdbcConnectionReleaseEnd() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcConnectionReleaseEnd();
		}
	}
