	@Override
	public void dirtyCalculationEnd(boolean dirty) {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.dirtyCalculationEnd( dirty );
		}
	}
