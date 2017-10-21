	@SuppressWarnings("")
	protected Object[] getPositionalParameterValues() {
		final Object[] explicitParameterValues = super.getPositionalParameterValues();
		final Object[] expandedParameterValues = new Object[ explicitParameterValues.length + 1 ];

		// previously this logic would only add an additional slot in the array, not fill it.  carry that logic here, for now
		System.arraycopy( explicitParameterValues, 0, expandedParameterValues, 1, explicitParameterValues.length );

		return expandedParameterValues;
	}
