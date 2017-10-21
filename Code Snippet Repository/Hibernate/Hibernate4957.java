	protected Object getComponentValue(ComponentType type, Object component, String propertyPath) {
		final int loc = propertyPath.indexOf( '.' );
		final String basePropertyName = loc > 0
				? propertyPath.substring( 0, loc )
				: propertyPath;
		final int index = findSubPropertyIndex( type, basePropertyName );
		final Object baseValue = type.getPropertyValue( component, index );
		if ( loc > 0 ) {
			if ( baseValue == null ) {
				return null;
			}
			return getComponentValue(
					(ComponentType) type.getSubtypes()[index],
					baseValue,
					propertyPath.substring( loc + 1 )
			);
		}
		else {
			return baseValue;
		}

	}
