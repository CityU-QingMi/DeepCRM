	@SuppressWarnings("")
	public FilterQueryPlan getFilterQueryPlan(
			String filterString,
			String collectionRole,
			boolean shallow,
			Map<String,Filter> enabledFilters) throws QueryException, MappingException {
		final FilterQueryPlanKey key =  new FilterQueryPlanKey( filterString, collectionRole, shallow, enabledFilters );
		FilterQueryPlan value = (FilterQueryPlan) queryPlanCache.get( key );
		if ( value == null ) {
			LOG.tracev(
					"Unable to locate collection-filter query plan in cache; generating ({0} : {1} )",
					collectionRole,
					filterString
			);
			value = new FilterQueryPlan( filterString, collectionRole, shallow, enabledFilters,factory );
			queryPlanCache.putIfAbsent( key, value );
		}
		else {
			LOG.tracev( "Located collection-filter query plan in cache ({0} : {1})", collectionRole, filterString );
		}
		return value;
	}
