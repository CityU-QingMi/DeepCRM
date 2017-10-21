	Object processCollection(Object collection, CollectionType type)
	throws HibernateException {
		
		if (collection==CollectionType.UNFETCHED_COLLECTION) {
			return null;
		}

		if (collection!=null) {
			final PersistentCollection coll;
			if ( type.hasHolder() ) {
				coll = getSession().getPersistenceContext().getCollectionHolder(collection);
			}
			else if ( collection == LazyPropertyInitializer.UNFETCHED_PROPERTY ) {
				coll = (PersistentCollection) type.resolve( collection, getSession(), owner );
			}
			else {
				coll = (PersistentCollection) collection;
			}

			Collections.processReachableCollection( coll, type, owner, getSession() );
		}

		return null;

	}
