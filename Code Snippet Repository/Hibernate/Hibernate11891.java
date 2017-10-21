	@SuppressWarnings("")
	public static Criterion SDOFilter(String propertyName, Geometry geom, Double minResolution, Double maxResolution) {
		if ( minResolution == null && maxResolution == null ) {
			return SDOFilter( propertyName, geom, null );
		}
		else {
			final SDOParameterMap param = new SDOParameterMap();
			param.setMinResolution( minResolution );
			param.setMaxResolution( maxResolution );
			return SDOFilter( propertyName, geom, param );
		}
	}
