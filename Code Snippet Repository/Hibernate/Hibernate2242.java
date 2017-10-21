	@SuppressWarnings("")
	public HQLQueryPlan getHQLQueryPlan(String queryString, boolean shallow, Map<String,Filter> enabledFilters)
			throws QueryException, MappingException {
		final HQLQueryPlanKey key = new HQLQueryPlanKey( queryString, shallow, enabledFilters );
		HQLQueryPlan value = (HQLQueryPlan) queryPlanCache.get( key );
		if ( value == null ) {
			LOG.tracev( "Unable to locate HQL query plan in cache; generating ({0})", queryString );
			value = new HQLQueryPlan( queryString, shallow, enabledFilters, factory );
			queryPlanCache.putIfAbsent( key, value );
		}
		else {
			LOG.tracev( "Located HQL query plan in cache ({0})", queryString );
		}
		return value;
	}
