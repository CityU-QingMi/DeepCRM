	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		if ( data == null || obj == null ) {
			return;
		}

		if ( propertyData.getBeanName() == null ) {
			// If properties are not encapsulated in a component but placed directly in a class
			// (e.g. by applying <properties> tag).
			delegate.mapToEntityFromMap( enversService, obj, data, primaryKey, versionsReader, revision );
			return;
		}

		final Setter setter = ReflectionTools.getSetter( obj.getClass(), propertyData, enversService.getServiceRegistry() );

		// If all properties are null and single, then the component has to be null also.
		boolean allNullAndSingle = true;
		for ( Map.Entry<PropertyData, PropertyMapper> property : delegate.getProperties().entrySet() ) {
			if ( data.get(
					property.getKey()
							.getName()
			) != null || !( property.getValue() instanceof SinglePropertyMapper ) ) {
				allNullAndSingle = false;
				break;
			}
		}

		if ( allNullAndSingle ) {
			// single property, but default value need not be null, so we'll set it to null anyway
			setter.set( obj, null, null );
		}
		else {
			// set the component
			try {
				final Object subObj = ReflectHelper.getDefaultConstructor( componentClass ).newInstance();
				setter.set( obj, subObj, null );
				delegate.mapToEntityFromMap( enversService, subObj, data, primaryKey, versionsReader, revision );
			}
			catch ( Exception e ) {
				throw new AuditException( e );
			}
		}
	}
