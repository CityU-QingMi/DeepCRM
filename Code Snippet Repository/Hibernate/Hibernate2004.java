	@Override
	public void partialFlushStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.partialFlushStart();
		}
	}
