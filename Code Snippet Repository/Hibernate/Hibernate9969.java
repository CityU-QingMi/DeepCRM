	@Override
	public CompositeMapperBuilder addComponent(PropertyData propertyData, Class componentClass) {
		if ( properties.get( propertyData ) != null ) {
			// This is needed for second pass to work properly in the components mapper
			return (CompositeMapperBuilder) properties.get( propertyData );
		}

		final ComponentPropertyMapper componentMapperBuilder = new ComponentPropertyMapper(
				propertyData,
				componentClass
		);
		addComposite( propertyData, componentMapperBuilder );

		return componentMapperBuilder;
	}
