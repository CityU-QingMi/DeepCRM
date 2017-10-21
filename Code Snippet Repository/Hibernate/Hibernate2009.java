	@Override
	public void end() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.end();
		}
	}
