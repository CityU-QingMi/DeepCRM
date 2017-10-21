	@Override
	public void execute() throws HibernateException {
		// this method is called when a new non-null collection is persisted
		// or when an existing (non-null) collection is moved to a new owner
		final PersistentCollection collection = getCollection();
		
		preRecreate();
		getPersister().recreate( collection, getKey(), getSession() );
		getSession().getPersistenceContext().getCollectionEntry( collection ).afterAction( collection );
		evict();
		postRecreate();

		if ( getSession().getFactory().getStatistics().isStatisticsEnabled() ) {
			getSession().getFactory().getStatistics().recreateCollection( getPersister().getRole() );
		}
	}
