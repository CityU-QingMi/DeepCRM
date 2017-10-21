	@Override
	public void execute() throws HibernateException {
		preRemove();

		if ( !emptySnapshot ) {
			// an existing collection that was either non-empty or uninitialized
			// is replaced by null or a different collection
			// (if the collection is uninitialized, hibernate has no way of
			// knowing if the collection is actually empty without querying the db)
			getPersister().remove( getKey(), getSession() );
		}
		
		final PersistentCollection collection = getCollection();
		if ( collection != null ) {
			getSession().getPersistenceContext().getCollectionEntry( collection ).afterAction( collection );
		}

		evict();
		postRemove();

		if ( getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
			getSession().getFactory().getStatistics().removeCollection( getPersister().getRole() );
		}
	}
