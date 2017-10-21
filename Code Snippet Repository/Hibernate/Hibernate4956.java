	@Override
	public Object getPropertyValue(Object entity, String propertyPath) throws HibernateException {
		int loc = propertyPath.indexOf( '.' );
		String basePropertyName = loc > 0
				? propertyPath.substring( 0, loc )
				: propertyPath;
		//final int index = entityMetamodel.getPropertyIndexOrNull( basePropertyName );
		Integer index = entityMetamodel.getPropertyIndexOrNull( basePropertyName );
		if ( index == null ) {
			propertyPath = PropertyPath.IDENTIFIER_MAPPER_PROPERTY + "." + propertyPath;
			loc = propertyPath.indexOf( '.' );
			basePropertyName = loc > 0
					? propertyPath.substring( 0, loc )
					: propertyPath;
		}
		index = entityMetamodel.getPropertyIndexOrNull( basePropertyName );
		final Object baseValue = getPropertyValue( entity, index );
		if ( loc > 0 ) {
			if ( baseValue == null ) {
				return null;
			}
			return getComponentValue(
					(ComponentType) entityMetamodel.getPropertyTypes()[index],
					baseValue,
					propertyPath.substring( loc + 1 )
			);
		}
		else {
			return baseValue;
		}
	}
