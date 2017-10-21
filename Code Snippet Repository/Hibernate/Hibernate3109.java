	@Override
	public org.hibernate.query.Query createFilter(Object collection, String queryString) {
		checkOpen();
		checkTransactionSynchStatus();
		CollectionFilterImpl filter = new CollectionFilterImpl(
				queryString,
				collection,
				this,
				getFilterQueryPlan( collection, queryString, null, false ).getParameterMetadata()
		);
		filter.setComment( queryString );
		delayedAfterCompletion();
		return filter;
	}
