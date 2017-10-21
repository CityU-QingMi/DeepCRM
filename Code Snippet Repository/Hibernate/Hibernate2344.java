	public QueryParameters(
			QueryParameterBindingsImpl queryParameterBindings,
			LockOptions lockOptions,
			RowSelection selection,
			final boolean isReadOnlyInitialized,
			boolean readOnly,
			boolean cacheable,
			String cacheRegion,
			String comment,
			List<String> dbHints,
			final Serializable[] collectionKeys,
			final Object optionalObject,
			final String optionalEntityName,
			final Serializable optionalId,
			ResultTransformer resultTransformer) {
		this(
				queryParameterBindings.collectPositionalBindTypes(),
				queryParameterBindings.collectPositionalBindValues(),
				queryParameterBindings.collectNamedParameterBindings(),
				lockOptions,
				selection,
				isReadOnlyInitialized,
				readOnly,
				cacheable,
				cacheRegion,
				comment,
				dbHints,
				collectionKeys,
				optionalObject,
				optionalEntityName,
				optionalId,
				resultTransformer
		);

	}
