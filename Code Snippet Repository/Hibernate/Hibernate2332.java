	public NamedQueryDefinition makeCopy(String name) {
		return new NamedQueryDefinition(
				name,
				getQuery(),
				isCacheable(),
				getCacheRegion(),
				getTimeout(),
				getLockOptions(),
				getFetchSize(),
				getFlushMode(),
				getCacheMode(),
				isReadOnly(),
				getComment(),
				getParameterTypes(),
				getFirstResult(),
				getMaxResults()
		);
	}
