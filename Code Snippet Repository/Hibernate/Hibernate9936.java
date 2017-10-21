	@Override
	public void onPostRecreateCollection(PostCollectionRecreateEvent event) {
		final CollectionEntry collectionEntry = getCollectionEntry( event );
		if ( !collectionEntry.getLoadedPersister().isInverse() ) {
			onCollectionAction( event, event.getCollection(), null, collectionEntry );
		}
		else {
			onCollectionActionInversed( event, event.getCollection(), null, collectionEntry );
		}
	}
