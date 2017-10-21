	public CascadeStyle getCascadeStyle() throws MappingException {
		Type type = value.getType();
		if ( type.isComponentType() ) {
			return getCompositeCascadeStyle( (CompositeType) type, cascade );
		}
		else if ( type.isCollectionType() ) {
			return getCollectionCascadeStyle( ( (Collection) value ).getElement().getType(), cascade );
		}
		else {
			return getCascadeStyle( cascade );			
		}
	}
