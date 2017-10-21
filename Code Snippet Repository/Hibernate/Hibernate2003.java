	@Override
	public void flushEnd(int numberOfEntities, int numberOfCollections) {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.flushEnd( numberOfEntities, numberOfCollections );
		}
	}
