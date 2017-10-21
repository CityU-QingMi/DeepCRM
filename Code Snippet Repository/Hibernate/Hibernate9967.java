	@Override
	public void mapModifiedFlagsToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		for ( Map.Entry<PropertyData, PropertyMapper> entry : properties.entrySet() ) {
			final PropertyData propertyData = entry.getKey();
			final PropertyMapper propertyMapper = entry.getValue();
			if ( newObj == null && oldObj == null ) {
				return;
			}
			Object newValue = newObj == null ? null : getValue( newObj, propertyData );
			Object oldValue = oldObj == null ? null : getValue( oldObj, propertyData );
			propertyMapper.mapModifiedFlagsToMapFromEntity( session, data, newValue, oldValue );
		}
	}
