	@Override
	public void cacheGetStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.cacheGetStart();
		}
	}
