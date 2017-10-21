	private static void copyStringAttribute(
			final AnnotationDescriptor annotation, final Element element,
			final String attributeName, final boolean mandatory) {
		copyStringAttribute(
				annotation,
				element,
				getJavaAttributeNameFromXMLOne( attributeName ),
				attributeName,
				mandatory
		);
	}
