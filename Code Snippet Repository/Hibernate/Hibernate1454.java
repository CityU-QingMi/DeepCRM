	private void preCalculateElementsForProperty(Element tree) {
		elementsForProperty = new ArrayList<Element>();
		Element element = tree != null ? tree.element( "attributes" ) : null;
		//put entity.attributes elements
		if ( element != null ) {
			for ( Element subelement : (List<Element>) element.elements() ) {
				if ( propertyName.equals( subelement.attributeValue( "name" ) ) ) {
					elementsForProperty.add( subelement );
				}
			}
		}
		//add pre-* etc from entity and pure entity listener classes
		if ( tree != null ) {
			for ( Element subelement : (List<Element>) tree.elements() ) {
				if ( propertyName.equals( subelement.attributeValue( "method-name" ) ) ) {
					elementsForProperty.add( subelement );
				}
			}
		}
	}
