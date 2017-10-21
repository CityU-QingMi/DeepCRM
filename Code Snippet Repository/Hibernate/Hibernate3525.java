	protected void putResultInQueryCache(
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final Type[] resultTypes,
			final QueryCache queryCache,
			final QueryKey key,
			final List result) {
		if ( session.getCacheMode().isPutEnabled() ) {
			boolean put = queryCache.put(
					key,
					key.getResultTransformer().getCachedResultTypes( resultTypes ),
					result,
					queryParameters.isNaturalKeyLookup(),
					session
			);
			if ( put && factory.getStatistics().isStatisticsEnabled() ) {
				factory.getStatistics().queryCachePut( getQueryIdentifier(), queryCache.getRegion().getName() );
			}
		}
	}
