	@Override
	@SuppressWarnings({""})
	public List list(Criteria criteria) throws HibernateException {
		// TODO: Is this guaranteed to always be CriteriaImpl?
		CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;

		checkOpen();
		String[] implementors = getFactory().getMetamodel().getImplementors( criteriaImpl.getEntityOrClassName() );
		int size = implementors.length;

		CriteriaLoader[] loaders = new CriteriaLoader[size];
		for ( int i = 0; i < size; i++ ) {
			loaders[i] = new CriteriaLoader(
					getOuterJoinLoadable( implementors[i] ),
					getFactory(),
					criteriaImpl,
					implementors[i],
					getLoadQueryInfluencers()
			);
		}


		List results = Collections.EMPTY_LIST;
		boolean success = false;
		try {
			for ( int i = 0; i < size; i++ ) {
				final List currentResults = loaders[i].list( this );
				currentResults.addAll( results );
				results = currentResults;
			}
			success = true;
		}
		finally {
			afterOperation( success );
		}
		temporaryPersistenceContext.clear();
		return results;
	}
