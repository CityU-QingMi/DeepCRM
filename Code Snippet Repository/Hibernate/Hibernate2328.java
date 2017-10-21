	public Type getFilterParameterType(String filterParameterName) {
		final String[] parsed = parseFilterParameterName( filterParameterName );
		final FilterDefinition filterDef = sessionFactory.getFilterDefinition( parsed[0] );
		if ( filterDef == null ) {
			throw new IllegalArgumentException( "Filter [" + parsed[0] + "] not defined" );
		}
		final Type type = filterDef.getParameterType( parsed[1] );
		if ( type == null ) {
			// this is an internal error of some sort...
			throw new InternalError( "Unable to locate type for filter parameter" );
		}
		return type;
	}
