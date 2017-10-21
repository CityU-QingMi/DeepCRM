	@Override
	@SuppressWarnings("")
	public QueryImplementor setProperties(Map map) {
		final String[] namedParameterNames = getNamedParameters();
		for ( String paramName : namedParameterNames ) {
			final Object object = map.get( paramName );
			if ( object == null ) {
				if ( map.containsKey( paramName ) ) {
					setParameter( paramName, null, determineType( paramName, null ) );
				}
			}
			else {
				Class retType = object.getClass();
				if ( Collection.class.isAssignableFrom( retType ) ) {
					setParameterList( paramName, (Collection) object );
				}
				else if ( retType.isArray() ) {
					setParameterList( paramName, (Object[]) object );
				}
				else {
					setParameter( paramName, object, determineType( paramName, retType ) );
				}
			}
		}
		return this;
	}
