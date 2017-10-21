	@Override
	public void onPreRemoveCollection(PreCollectionRemoveEvent event) {
		final CollectionEntry collectionEntry = getCollectionEntry( event );
		if ( collectionEntry != null ) {
			if ( !collectionEntry.getLoadedPersister().isInverse() ) {
				Serializable oldColl = collectionEntry.getSnapshot();
				if ( !event.getCollection().wasInitialized() && shouldGenerateRevision( event ) ) {
					// In case of uninitialized collection we need a fresh snapshot to properly calculate audit data.
					oldColl = initializeCollection( event );
				}
				onCollectionAction( event, null, oldColl, collectionEntry );
			}
			else {
				// HHH-7510 - Avoid LazyInitializationException when global_with_modified_flag = true
				if ( getEnversService().getGlobalConfiguration().isGlobalWithModifiedFlag() ) {
					initializeCollection( event );
				}
			}
		}
	}
