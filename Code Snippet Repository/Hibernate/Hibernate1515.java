	private static void copyIntegerAttribute(AnnotationDescriptor annotation, Element element, String attributeName) {
		String attribute = element.attributeValue( attributeName );
		if ( attribute != null ) {
			String annotationAttributeName = getJavaAttributeNameFromXMLOne( attributeName );
			annotation.setValue( annotationAttributeName, attribute );
			try {
				int length = Integer.parseInt( attribute );
				annotation.setValue( annotationAttributeName, length );
			}
			catch ( NumberFormatException e ) {
				throw new AnnotationException(
						element.getPath() + attributeName + " not parseable: " + attribute + " (" + SCHEMA_VALIDATION + ")"
				);
			}
		}
	}
