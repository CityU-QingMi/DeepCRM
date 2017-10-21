	@Override
	public boolean mapToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		boolean ret = false;
		for ( Map.Entry<PropertyData, PropertyMapper> entry : properties.entrySet() ) {
			final PropertyData propertyData = entry.getKey();
			final PropertyMapper propertyMapper = entry.getValue();
			if ( newObj == null && oldObj == null ) {
				return false;
			}
			Object newValue = newObj == null ? null : getValue( newObj, propertyData );
			Object oldValue = oldObj == null ? null : getValue( oldObj, propertyData );

			ret |= propertyMapper.mapToMapFromEntity( session, data, newValue, oldValue );
		}

		return ret;
	}
