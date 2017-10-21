	@Deprecated
	public NamedSQLQueryDefinition(
			String name,
			String query,
			String resultSetRef,
			List<String> querySpaces,
			boolean cacheable,
			String cacheRegion,
			Integer timeout,
			Integer fetchSize,
			FlushMode flushMode,
			CacheMode cacheMode,
			boolean readOnly,
			String comment,
			Map parameterTypes,
			boolean callable) {

		this(
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
				null,		// firstResult
				null,		// maxResults
				resultSetRef,
				querySpaces,
				callable,
				null		// queryReturns
		);
	}
