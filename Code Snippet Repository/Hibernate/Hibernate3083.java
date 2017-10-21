	private Serializable fireSave(SaveOrUpdateEvent event) {
		checkOpen();
		checkTransactionSynchStatus();
		checkNoUnresolvedActionsBeforeOperation();
		for ( SaveOrUpdateEventListener listener : listeners( EventType.SAVE ) ) {
			listener.onSaveOrUpdate( event );
		}
		checkNoUnresolvedActionsAfterOperation();
		return event.getResultId();
	}
