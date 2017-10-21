	@Override
	public NamedSQLQueryDefinition makeCopy(String name) {
		return new NamedSQLQueryDefinition(
				name,
				getQuery(),
				isCacheable(),
				getCacheRegion(),
				getTimeout(),
				getFetchSize(),
				getFlushMode(),
				getCacheMode(),
				isReadOnly(),
				getComment(),
				getParameterTypes(),
				getFirstResult(),
				getMaxResults(),
				getResultSetRef(),
				getQuerySpaces(),
				isCallable(),
				getQueryReturns()
		);
	}
