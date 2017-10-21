	@SuppressWarnings("")
	public NativeSQLQueryPlan getNativeSQLQueryPlan(final NativeSQLQuerySpecification spec) {
		NativeSQLQueryPlan value = (NativeSQLQueryPlan) queryPlanCache.get( spec );
		if ( value == null ) {
			LOG.tracev( "Unable to locate native-sql query plan in cache; generating ({0})", spec.getQueryString() );
			value = nativeQueryInterpreter.createQueryPlan( spec, factory );
			queryPlanCache.putIfAbsent( spec, value );
		}
		else {
			LOG.tracev( "Located native-sql query plan in cache ({0})", spec.getQueryString() );
		}
		return value;
	}
