	@Override
	public Type getType(String relativePath) {
		// TODO: can a component have a nested component? then we may need to do something more here...
		if ( relativePath.indexOf( '.' ) >= 0 ) {
			throw new IllegalArgumentException( "dotted paths not handled (yet?!) for collection-of-component" );
		}
		Type type = subTypes.get( relativePath );
		if ( type == null ) {
			throw new IllegalArgumentException( "property " + relativePath + " not found in component of collection " + getName() );
		}
		return type;
	}
