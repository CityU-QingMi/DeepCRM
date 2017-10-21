	@Override
	public void transactionCompletion(boolean successful) {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.transactionCompletion( successful );
		}
	}
