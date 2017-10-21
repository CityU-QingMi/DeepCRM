	@Override
	public int executeNativeUpdate(
			NativeSQLQuerySpecification nativeQuerySpecification,
			QueryParameters queryParameters) throws HibernateException {
		checkOpenOrWaitingForAutoClose();
		checkTransactionSynchStatus();
		queryParameters.validateParameters();
		NativeSQLQueryPlan plan = getNativeQueryPlan( nativeQuerySpecification );


		autoFlushIfRequired( plan.getCustomQuery().getQuerySpaces() );

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
