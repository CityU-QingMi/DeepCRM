	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		// synthetic properties are not part of the entity model; therefore they should be ignored.
		if ( data == null || obj == null || propertyData.isSynthetic() ) {
			return;
		}

		final Setter setter = ReflectionTools.getSetter( obj.getClass(), propertyData, enversService.getServiceRegistry() );
		final Object value = data.get( propertyData.getName() );
		// We only set a null value if the field is not primite. Otherwise, we leave it intact.
		if ( value != null || !isPrimitive( setter, propertyData, obj.getClass() ) ) {
			setter.set( obj, value, null );
		}
	}
