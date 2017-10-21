	public QueryParameters(
			final Type[] positionalParameterTypes,
			final Object[] positionalParameterValues,
			final Map<String,TypedValue> namedParameters,
			final LockOptions lockOptions,
			final RowSelection rowSelection,
			final boolean isReadOnlyInitialized,
			final boolean readOnly,
			final boolean cacheable,
			final String cacheRegion,
			//final boolean forceCacheRefresh,
			final String comment,
			final List<String> queryHints,
			final Serializable[] collectionKeys,
			final Object optionalObject,
			final String optionalEntityName,
			final Serializable optionalId,
			final ResultTransformer transformer) {
		this(
				positionalParameterTypes,
				positionalParameterValues,
				namedParameters,
				lockOptions,
				rowSelection,
				isReadOnlyInitialized,
				readOnly,
				cacheable,
				cacheRegion,
				comment,
				queryHints,
				collectionKeys,
				transformer
		);
		this.optionalEntityName = optionalEntityName;
		this.optionalId = optionalId;
		this.optionalObject = optionalObject;
	}
