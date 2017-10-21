	@Override
	public List list(String query, QueryParameters queryParameters) throws HibernateException {
		checkOpen();
		queryParameters.validateParameters();
		HQLQueryPlan plan = getQueryPlan( query, false );
		boolean success = false;
		List results = Collections.EMPTY_LIST;
		try {
			results = plan.performList( queryParameters, this );
			success = true;
		}
		finally {
			afterOperation( success );
		}
		temporaryPersistenceContext.clear();
		return results;
	}
