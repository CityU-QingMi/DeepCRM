	@Override
	public int executeNativeUpdate(
			NativeSQLQuerySpecification nativeSQLQuerySpecification,
			QueryParameters queryParameters) throws HibernateException {
		checkOpen();
		queryParameters.validateParameters();
		NativeSQLQueryPlan plan = getNativeQueryPlan( nativeSQLQuerySpecification );

		boolean success = false;
		int result = 0;
		try {
			result = plan.performExecuteUpdate( queryParameters, this );
			success = true;
		}
		finally {
			afterOperation( success );
		}
		temporaryPersistenceContext.clear();
		return result;
	}
