	@Override
	public void dirtyCalculationStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.dirtyCalculationStart();
		}
	}
