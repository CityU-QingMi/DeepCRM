	private void preRemove() {
		final EventListenerGroup<PreCollectionRemoveEventListener> listenerGroup = listenerGroup( EventType.PRE_COLLECTION_REMOVE );
		if ( listenerGroup.isEmpty() ) {
			return;
		}
		final PreCollectionRemoveEvent event = new PreCollectionRemoveEvent(
				getPersister(),
				getCollection(),
				eventSource(),
				affectedOwner
		);
		for ( PreCollectionRemoveEventListener listener : listenerGroup.listeners() ) {
			listener.onPreRemoveCollection( event );
		}
	}
