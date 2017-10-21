	@Override
	Object processCollection(Object collection, CollectionType collectionType)
			throws HibernateException {

		if ( collection != null && ( collection instanceof PersistentCollection ) ) {

			final SessionImplementor session = getSession();
			PersistentCollection coll = (PersistentCollection) collection;
			if ( coll.setCurrentSession( session ) ) {
				reattachCollection( coll, collectionType );
			}
			return null;

		}
		else {
			return processArrayOrNewCollection( collection, collectionType );
		}

	}
