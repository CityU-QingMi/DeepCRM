	@Override
	Object processCollection(Object collection, CollectionType type) throws HibernateException {

		if ( collection == CollectionType.UNFETCHED_COLLECTION ) {
			return null;
		}

		EventSource session = getSession();
		CollectionPersister persister = session.getFactory().getCollectionPersister( type.getRole() );

		final Serializable collectionKey = extractCollectionKeyFromOwner( persister );
		if ( collection!=null && (collection instanceof PersistentCollection) ) {
			PersistentCollection wrapper = (PersistentCollection) collection;
			if ( wrapper.setCurrentSession(session) ) {
				//a "detached" collection!
				if ( !isOwnerUnchanged( wrapper, persister, collectionKey ) ) {
					// if the collection belonged to a different entity,
					// clean up the existing state of the collection
					removeCollection( persister, collectionKey, session );
				}
				reattachCollection(wrapper, type);
			}
			else {
				// a collection loaded in the current session
				// can not possibly be the collection belonging
				// to the entity passed to update()
				removeCollection(persister, collectionKey, session);
			}
		}
		else {
			// null or brand new collection
			// this will also (inefficiently) handle arrays, which have
			// no snapshot, so we can't do any better
			removeCollection(persister, collectionKey, session);
		}

		return null;
	}
