	@Override
	public ScrollableResultsImplementor scroll(String query, QueryParameters queryParameters) throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();

		HQLQueryPlan plan = queryParameters.getQueryPlan();
		if ( plan == null ) {
			plan = getQueryPlan( query, false );
		}

		autoFlushIfRequired( plan.getQuerySpaces() );

		dontFlushFromFind++;
		try {
			return plan.performScroll( queryParameters, this );
		}
		finally {
			delayedAfterCompletion();
			dontFlushFromFind--;
		}
	}
