	private QueryKey generateQueryKey(
			SharedSessionContractImplementor session,
			QueryParameters queryParameters) {
		return QueryKey.generateQueryKey(
				getSQLString(),
				queryParameters,
				FilterKey.createFilterKeys( session.getLoadQueryInfluencers().getEnabledFilters() ),
				session,
				createCacheableResultTransformer( queryParameters )
		);
	}
