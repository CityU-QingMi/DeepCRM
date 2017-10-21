	void removeCollection(CollectionPersister role, Serializable collectionKey, EventSource source)
			throws HibernateException {
		if ( LOG.isTraceEnabled() ) {
			LOG.tracev(
					"Collection dereferenced while transient {0}",
					MessageHelper.collectionInfoString( role, ownerIdentifier, source.getFactory() )
			);
		}
		source.getActionQueue().addAction( new CollectionRemoveAction( owner, role, collectionKey, false, source ) );
	}
