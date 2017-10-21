	public NamedQueryDefinition createNamedQueryDefinition() {
		return new NamedQueryDefinition(
				name,
				query,
				cacheable,
				cacheRegion,
				timeout,
				lockOptions,
				fetchSize,
				flushMode,
				cacheMode,
				readOnly,
				comment,
				parameterTypes,
				firstResult,
				maxResults
		);
	}
