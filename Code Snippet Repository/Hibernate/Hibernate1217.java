	private static PropertyData getUniqueIdPropertyFromBaseClass(
			PropertyData inferredData,
			PropertyData baseInferredData,
			AccessType propertyAccessor,
			MetadataBuildingContext context) {
		List<PropertyData> baseClassElements = new ArrayList<PropertyData>();
		XClass baseReturnedClassOrElement = baseInferredData.getClassOrElement();
		PropertyContainer propContainer = new PropertyContainer(
				baseReturnedClassOrElement,
				inferredData.getPropertyClass(),
				propertyAccessor
		);
		addElementsOfClass( baseClassElements, propContainer, context );
		//Id properties are on top and there is only one
		return baseClassElements.get( 0 );
	}
