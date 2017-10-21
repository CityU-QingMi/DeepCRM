	public QueryParameters(
			final Type[] positionalParameterTypes,
			final Object[] positionalParameterValues,
			final LockOptions lockOptions,
			final RowSelection rowSelection,
			final boolean isReadOnlyInitialized,
			final boolean readOnly,
			final boolean cacheable,
			final String cacheRegion,
			//final boolean forceCacheRefresh,
			final String comment,
			final List<String> queryHints,
			final boolean isLookupByNaturalKey,
			final ResultTransformer transformer) {
		this(
				positionalParameterTypes,
				positionalParameterValues,
				null,
				lockOptions,
				rowSelection,
				isReadOnlyInitialized,
				readOnly,
				cacheable,
				cacheRegion,
				comment,
				queryHints,
				null,
				transformer
		);
		isNaturalKeyLookup = isLookupByNaturalKey;
	}
