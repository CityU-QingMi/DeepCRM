	@Deprecated
	public NamedQueryDefinition(
			String name,
			String query,
			boolean cacheable,
			String cacheRegion,
			Integer timeout,
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
				LockOptions.WAIT_FOREVER,
				fetchSize,
				flushMode,
				cacheMode,
				readOnly,
				comment,
				parameterTypes
		);
	}
