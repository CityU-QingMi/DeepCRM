	public OrdinalParameterDescriptor getOrdinalParameterDescriptor(int position) {
		if ( !isOrdinalParametersZeroBased ) {
			position--;
		}
		if ( position < 0 || position >= ordinalDescriptors.length ) {
			throw new QueryParameterException(
					"Position beyond number of declared ordinal parameters. " +
							"Remember that ordinal parameters are 0-based! Position: " + position
			);
		}
		return ordinalDescriptors[position];
	}
