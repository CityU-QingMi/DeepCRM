	@Override
	public void cachePutStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.cachePutStart();
		}
	}
