	@Override
	public Object processCollection(Object collection, CollectionType type) throws HibernateException {
		if ( collection == CollectionType.UNFETCHED_COLLECTION ) {
			return null;
		}

		final EventSource session = getSession();
		final CollectionPersister persister = session.getFactory().getMetamodel().collectionPersister( type.getRole() );

		if ( isUpdate ) {
			removeCollection( persister, extractCollectionKeyFromOwner( persister ), session );
		}
		if ( collection != null && collection instanceof PersistentCollection ) {
			final PersistentCollection wrapper = (PersistentCollection) collection;
			wrapper.setCurrentSession( (SessionImplementor) session );
			if ( wrapper.wasInitialized() ) {
				session.getPersistenceContext().addNewCollection( persister, wrapper );
			}
			else {
				reattachCollection( wrapper, type );
			}
		}
		else {
			// otherwise a null or brand new collection
			// this will also (inefficiently) handle arrays, which
			// have no snapshot, so we can't do any better
			//processArrayOrNewCollection(collection, type);
		}

		return null;

	}
