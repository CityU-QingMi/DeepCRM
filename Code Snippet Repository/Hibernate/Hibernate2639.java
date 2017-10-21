	@Override
	public Type getDataType() {
		if ( super.getDataType() == null ) {
			FromElement fromElement = getLhs().getFromElement();
			if ( fromElement == null ) {
				return null;
			}
			// If the lhs is a collection, use CollectionPropertyMapping
			Type propertyType = fromElement.getPropertyType( propertyName, propertyPath );
			LOG.debugf( "getDataType() : %s -> %s", propertyPath, propertyType );
			super.setDataType( propertyType );
		}
		return super.getDataType();
	}
