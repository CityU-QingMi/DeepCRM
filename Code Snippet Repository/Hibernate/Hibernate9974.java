	private Pair<PropertyMapper, String> getMapperAndDelegatePropName(String referencingPropertyName) {
		// Name of the property, to which we will delegate the mapping.
		String delegatePropertyName;

		// Checking if the property name doesn't reference a collection in a component - then the name will containa a .
		final int dotIndex = referencingPropertyName.indexOf( '.' );
		if ( dotIndex != -1 ) {
			// Computing the name of the component
			final String componentName = referencingPropertyName.substring( 0, dotIndex );
			// And the name of the property in the component
			final String propertyInComponentName = MappingTools.createComponentPrefix( componentName )
					+ referencingPropertyName.substring( dotIndex + 1 );

			// We need to get the mapper for the component.
			referencingPropertyName = componentName;
			// As this is a component, we delegate to the property in the component.
			delegatePropertyName = propertyInComponentName;
		}
		else {
			// If this is not a component, we delegate to the same property.
			delegatePropertyName = referencingPropertyName;
		}
		return Pair.make( properties.get( propertyDatas.get( referencingPropertyName ) ), delegatePropertyName );
	}
