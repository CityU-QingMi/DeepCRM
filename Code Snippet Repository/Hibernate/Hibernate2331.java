	@Deprecated
	public NamedQueryDefinition(
			String name,
			String query,
			boolean cacheable,
			String cacheRegion,
			Integer timeout,
			Integer lockTimeout,
			Integer fetchSize,
			FlushMode flushMode,
			CacheMode cacheMode,
			boolean readOnly,
			String comment,
			Map parameterTypes) {
		this(
				name,
				query,
				cacheable,
				cacheRegion,
				timeout,
				new LockOptions().setTimeOut( lockTimeout ),
				fetchSize,
				flushMode,
				cacheMode,
				readOnly,
				comment,
				parameterTypes,
				null,		// firstResult
				null		// maxResults
		);
	}
