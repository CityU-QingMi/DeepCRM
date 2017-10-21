	@Override
	public void onPreUpdateCollection(PreCollectionUpdateEvent event) {
		final CollectionEntry collectionEntry = getCollectionEntry( event );
		if ( !collectionEntry.getLoadedPersister().isInverse() ) {
			onCollectionAction( event, event.getCollection(), collectionEntry.getSnapshot(), collectionEntry );
		}
		else {
			onCollectionActionInversed( event, event.getCollection(), collectionEntry.getSnapshot(), collectionEntry );
		}
	}
