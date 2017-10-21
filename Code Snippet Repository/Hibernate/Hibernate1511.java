	private Annotation getSequenceGenerator(List<Element> elementsForProperty, XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			Element subelement = element != null ? element.element( annotationToXml.get( SequenceGenerator.class ) ) : null;
			if ( subelement != null ) {
				return buildSequenceGeneratorAnnotation( subelement );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( SequenceGenerator.class );
		}
		else {
			return null;
		}
	}
