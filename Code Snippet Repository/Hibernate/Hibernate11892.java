	@SuppressWarnings("")
	public static Criterion SDONN(String propertyName, Geometry geom, Double distance, Integer numResults, String unit) {
		if ( distance == null && numResults == null && unit == null ) {
			return SDONN( propertyName, geom, null );
		}
		else {
			final SDOParameterMap param = new SDOParameterMap();
			param.setDistance( distance );
			param.setSdoNumRes( numResults );
			param.setUnit( unit );
			return SDONN( propertyName, geom, param );
		}
	}
