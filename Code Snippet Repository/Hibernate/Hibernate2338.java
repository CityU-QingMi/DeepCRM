	@Override
	public NamedSQLQueryDefinition createNamedQueryDefinition() {
		return new NamedSQLQueryDefinition(
				name,
				query,
				cacheable,
				cacheRegion,
				timeout,
				fetchSize,
				flushMode,
				cacheMode,
				readOnly,
				comment,
				parameterTypes,
				firstResult,
				maxResults,
				resultSetRef,
				querySpacesCopy(),
				callable,
				queryReturns
		);
	}
