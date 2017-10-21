	@Override
	public int executeUpdate(String query, QueryParameters queryParameters) throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		queryParameters.validateParameters();
		HQLQueryPlan plan = getQueryPlan( query, false );
		autoFlushIfRequired( plan.getQuerySpaces() );

		boolean success = false;
		int result = 0;
		try {
			result = plan.performExecuteUpdate( queryParameters, this );
			success = true;
		}
		finally {
			afterOperation( success );
			delayedAfterCompletion();
		}
		return result;
	}
