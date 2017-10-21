	@Override
	public int executeUpdate(String query, QueryParameters queryParameters) throws HibernateException {
		checkOpen();
		queryParameters.validateParameters();
		HQLQueryPlan plan = getQueryPlan( query, false );
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
