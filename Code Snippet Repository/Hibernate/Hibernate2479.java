	Object processCollection(Object collection, CollectionType type) throws HibernateException {
		if ( collection != null ) {
			final SessionImplementor session = getSession();
			final PersistentCollection persistentCollection;
			if ( type.isArrayType() ) {
				persistentCollection = session.getPersistenceContext().getCollectionHolder( collection );
				// if no array holder we found an unwrappered array (this can't occur,
				// because we now always call wrap() before getting to here)
				// return (ah==null) ? true : searchForDirtyCollections(ah, type);
			}
			else {
				// if not wrappered yet, its dirty (this can't occur, because
				// we now always call wrap() before getting to here)
				// return ( ! (obj instanceof PersistentCollection) ) ?
				//true : searchForDirtyCollections( (PersistentCollection) obj, type );
				persistentCollection = (PersistentCollection) collection;
			}

			if ( persistentCollection.isDirty() ) { //we need to check even if it was not initialized, because of delayed adds!
				dirty = true;
				return null; //NOTE: EARLY EXIT!
			}
		}

		return null;
	}
