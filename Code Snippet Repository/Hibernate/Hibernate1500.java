	private Annotation getTableGenerator(List<Element> elementsForProperty, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			Element subelement = element != null ? element.element( annotationToXml.get( TableGenerator.class ) ) : null;
			if ( subelement != null ) {
				return buildTableGeneratorAnnotation( subelement, defaults );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( TableGenerator.class );
		}
		else {
			return null;
		}
	}
