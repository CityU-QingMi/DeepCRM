	private boolean isLobMapElementType() {
		if ( propertyValue instanceof org.hibernate.mapping.Map ) {
			final Type type = propertyValue.getElement().getType();
			// we're only interested in basic types
			if ( !type.isComponentType() && !type.isAssociationType() ) {
				return ( type instanceof MaterializedClobType ) || ( type instanceof MaterializedNClobType );
			}
		}
		return false;
	}
