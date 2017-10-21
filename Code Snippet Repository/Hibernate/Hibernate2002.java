	@Override
	public void flushStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.flushStart();
		}
	}
