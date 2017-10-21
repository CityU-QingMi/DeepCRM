	@Override
	public void partialFlushEnd(int numberOfEntities, int numberOfCollections) {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.partialFlushEnd( numberOfEntities, numberOfCollections );
		}
	}
