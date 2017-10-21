	@Override
	public void mapModifiedFlagsToMapFromEntity(
			SessionImplementor session,
			Map<String, Object> data,
			Object newObj,
			Object oldObj) {
		for ( Map.Entry<PropertyData, PropertyMapper> entry : properties.entrySet() ) {
			final PropertyData propertyData = entry.getKey();
			final PropertyMapper propertyMapper = entry.getValue();

			// synthetic properties are not part of the entity model; therefore they should be ignored.
			if ( propertyData.isSynthetic() ) {
				continue;
			}

			Getter getter;
			if ( newObj != null ) {
				getter = ReflectionTools.getGetter( newObj.getClass(), propertyData, session.getFactory().getServiceRegistry() );
			}
			else if ( oldObj != null ) {
				getter = ReflectionTools.getGetter( oldObj.getClass(), propertyData, session.getFactory().getServiceRegistry() );
			}
			else {
				return;
			}
			propertyMapper.mapModifiedFlagsToMapFromEntity(
					session, data,
					newObj == null ? null : getter.get( newObj ),
					oldObj == null ? null : getter.get( oldObj )
			);
		}
	}
