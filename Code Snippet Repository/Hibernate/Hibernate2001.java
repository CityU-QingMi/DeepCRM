	@Override
	public void cacheGetEnd(boolean hit) {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.cacheGetEnd( hit );
		}
	}
