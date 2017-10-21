	@Override
	protected void putResultInQueryCache(
			final SharedSessionContractImplementor session,
			final QueryParameters queryParameters,
			final Type[] resultTypes,
			final QueryCache queryCache,
			final QueryKey key,
			final List result) {
		super.putResultInQueryCache( session, queryParameters, this.resultTypes, queryCache, key, result );
	}
