	private static void copyStringAttribute(
			final AnnotationDescriptor annotation, final Element element,
			final String annotationAttributeName, final String attributeName, boolean mandatory) {
		String attribute = element.attributeValue( attributeName );
		if ( attribute != null ) {
			annotation.setValue( annotationAttributeName, attribute );
		}
		else {
			if ( mandatory ) {
				throw new AnnotationException(
						element.getName() + "." + attributeName + " is mandatory in XML overriding. " + SCHEMA_VALIDATION
				);
			}
		}
	}
