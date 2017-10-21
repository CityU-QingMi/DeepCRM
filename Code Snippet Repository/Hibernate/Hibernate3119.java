	@Override
	public void initializeCollection(PersistentCollection collection, boolean writing) {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		InitializeCollectionEvent event = new InitializeCollectionEvent( collection, this );
		for ( InitializeCollectionEventListener listener : listeners( EventType.INIT_COLLECTION ) ) {
			listener.onInitializeCollection( event );
		}
		delayedAfterCompletion();
	}
