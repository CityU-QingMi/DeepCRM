	@SuppressWarnings({""})
	public void addComponent(
			Element parent, PropertyAuditingData propertyAuditingData,
			Value value, CompositeMapperBuilder mapper, String entityName,
			EntityXmlMappingData xmlMappingData, boolean firstPass) {
		final Component propComponent = (Component) value;

		final Class componentClass;
		if ( propComponent.isDynamic() ) {
			componentClass = ReflectionTools.loadClass(
					Map.class.getCanonicalName(),
					mainGenerator.getClassLoaderService()
			);

		}
		else {
			componentClass = ReflectionTools.loadClass(
					propComponent.getComponentClassName(),
					mainGenerator.getClassLoaderService()
			);
		}
		final CompositeMapperBuilder componentMapper = mapper.addComponent(
				propertyAuditingData.getPropertyData(),
				componentClass
		);

		// The property auditing data must be for a component.
		final ComponentAuditingData componentAuditingData = (ComponentAuditingData) propertyAuditingData;

		// Adding all properties of the component
		final Iterator<Property> properties = (Iterator<Property>) propComponent.getPropertyIterator();
		while ( properties.hasNext() ) {
			final Property property = properties.next();

			final PropertyAuditingData componentPropertyAuditingData =
					componentAuditingData.getPropertyAuditingData( property.getName() );

			// Checking if that property is audited
			if ( componentPropertyAuditingData != null ) {
				mainGenerator.addValue(
						parent, property.getValue(), componentMapper, entityName, xmlMappingData,
						componentPropertyAuditingData, property.isInsertable(), firstPass, false
				);
			}
		}
	}
