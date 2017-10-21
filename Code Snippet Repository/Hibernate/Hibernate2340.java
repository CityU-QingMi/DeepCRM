	public QueryParameters(
			final Type[] positionalParameterTypes,
			final Object[] positionalParameterValues,
			final Map<String,TypedValue> namedParameters,
			final Serializable[] collectionKeys) {
		this(
				positionalParameterTypes,
				positionalParameterValues,
				namedParameters,
				null,
				null,
				false,
				false,
				false,
				null,
				null,
				null,
				collectionKeys,
				null
		);
	}
