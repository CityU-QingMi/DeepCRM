	private Map buildNamedParameterLocMap(QueryParameters queryParameters) {
		if ( queryParameters.getNamedParameters() != null ) {
			final Map namedParameterLocMap = new HashMap();
			for ( String name : queryParameters.getNamedParameters().keySet() ) {
				namedParameterLocMap.put(
						name,
						getNamedParameterLocs( name )
				);
			}
			return namedParameterLocMap;
		}
		else {
			return null;
		}
	}
