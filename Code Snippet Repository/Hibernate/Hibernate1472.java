	private Annotation getMarkerAnnotation(
			Class<? extends Annotation> clazz, Element element, XMLContext.Default defaults
	) {
		Element subelement = element == null ? null : element.element( annotationToXml.get( clazz ) );
		if ( subelement != null ) {
			return AnnotationFactory.create( new AnnotationDescriptor( clazz ) );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			//TODO wonder whether it should be excluded so that user can undone it
			return getPhysicalAnnotation( clazz );
		}
		else {
			return null;
		}
	}
