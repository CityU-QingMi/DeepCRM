	public void evictCollection(Object value, CollectionType type) {
		final Object pc;
		if ( type.hasHolder() ) {
			pc = getSession().getPersistenceContext().removeCollectionHolder(value);
		}
		else if ( value instanceof PersistentCollection ) {
			pc = value;
		}
		else {
			return; //EARLY EXIT!
		}

		PersistentCollection collection = (PersistentCollection) pc;
		if ( collection.unsetSession( getSession() ) ) {
			evictCollection(collection);
		}
	}
