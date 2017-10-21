	@Override
	public List list(String query, QueryParameters queryParameters) throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		queryParameters.validateParameters();

		HQLQueryPlan plan = queryParameters.getQueryPlan();
		if ( plan == null ) {
			plan = getQueryPlan( query, false );
		}

		autoFlushIfRequired( plan.getQuerySpaces() );

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
