	public QueryParameters(
			final Type[] positionalParameterTypes,
			final Object[] positionalParameterValues,
			final Object optionalObject,
			final String optionalEntityName,
			final Serializable optionalObjectId) {
		this( positionalParameterTypes, positionalParameterValues );
		this.optionalObject = optionalObject;
		this.optionalId = optionalObjectId;
		this.optionalEntityName = optionalEntityName;

	}
