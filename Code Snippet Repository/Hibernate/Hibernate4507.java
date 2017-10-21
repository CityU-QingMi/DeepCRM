	@Override
	protected Type[] getPositionalParameterTypes() {
		final Type[] explicitParameterTypes = super.getPositionalParameterTypes();
		final Type[] expandedParameterTypes = new Type[ explicitParameterTypes.length + 1 ];

		// previously this logic would only add an additional slot in the array, not fill it.  carry that logic here, for now
		System.arraycopy( explicitParameterTypes, 0, expandedParameterTypes, 1, explicitParameterTypes.length );

		return expandedParameterTypes;
	}
