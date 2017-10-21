	@Override
	public List listFilter(Object collection, String filter, QueryParameters queryParameters) {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		FilterQueryPlan plan = getFilterQueryPlan( collection, filter, queryParameters, false );
		List results = Collections.EMPTY_LIST;

		boolean success = false;
		dontFlushFromFind++;   //stops flush being called multiple times if this method is recursively called
		try {
			results = plan.performList( queryParameters, this );
			success = true;
		}
		finally {
			dontFlushFromFind--;
			afterOperation( success );
			delayedAfterCompletion();
		}
		return results;
	}
