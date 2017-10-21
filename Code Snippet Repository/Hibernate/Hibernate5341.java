	private QueryKey buildBasicKey(CacheableResultTransformer resultTransformer) {
		return new QueryKey(
				QUERY_STRING,
				ArrayHelper.EMPTY_TYPE_ARRAY, 		// positional param types
				ArrayHelper.EMPTY_OBJECT_ARRAY,		// positional param values
				Collections.EMPTY_MAP,				// named params
				null,								// firstRow selection
				null,								// maxRows selection
				Collections.EMPTY_SET, 				// filter keys
				null,								// tenantIdentifier
				resultTransformer					// the result transformer
		);
	}
