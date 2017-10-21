	public static Map<String, int[]> buildNamedParameterLocMap(
			QueryParameters queryParameters,
			NamedParameterContext namedParameterContext) {
		if ( queryParameters.getNamedParameters() == null || queryParameters.getNamedParameters().isEmpty() ) {
			return null;
		}

		final Map<String, int[]> namedParameterLocMap = new HashMap<>();
		for ( String name : queryParameters.getNamedParameters().keySet() ) {
			namedParameterLocMap.put(
					name,
					namedParameterContext.getNamedParameterLocations( name )
			);
		}
		return namedParameterLocMap;
	}
