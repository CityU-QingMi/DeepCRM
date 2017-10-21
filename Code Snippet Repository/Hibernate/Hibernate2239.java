	@SuppressWarnings("")
	public QueryPlanCache(final SessionFactoryImplementor factory) {
		this.factory = factory;

		Integer maxParameterMetadataCount = ConfigurationHelper.getInteger(
				Environment.QUERY_PLAN_CACHE_PARAMETER_METADATA_MAX_SIZE,
				factory.getProperties()
		);
		if ( maxParameterMetadataCount == null ) {
			maxParameterMetadataCount = ConfigurationHelper.getInt(
					Environment.QUERY_PLAN_CACHE_MAX_STRONG_REFERENCES,
					factory.getProperties(),
					DEFAULT_PARAMETER_METADATA_MAX_COUNT
			);
		}
		Integer maxQueryPlanCount = ConfigurationHelper.getInteger(
				Environment.QUERY_PLAN_CACHE_MAX_SIZE,
				factory.getProperties()
		);
		if ( maxQueryPlanCount == null ) {
			maxQueryPlanCount = ConfigurationHelper.getInt(
					Environment.QUERY_PLAN_CACHE_MAX_SOFT_REFERENCES,
					factory.getProperties(),
					DEFAULT_QUERY_PLAN_MAX_COUNT
			);
		}

		queryPlanCache = new BoundedConcurrentHashMap( maxQueryPlanCount, 20, BoundedConcurrentHashMap.Eviction.LIRS );
		parameterMetadataCache = new BoundedConcurrentHashMap<>(
				maxParameterMetadataCount,
				20,
				BoundedConcurrentHashMap.Eviction.LIRS
		);

		nativeQueryInterpreter = factory.getServiceRegistry().getService( NativeQueryInterpreter.class );
	}
