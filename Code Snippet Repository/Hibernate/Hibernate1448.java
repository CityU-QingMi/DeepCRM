	private Transient getTransient(XMLContext.Default defaults) {
		for ( Element element : elementsForProperty ) {
			if ( "transient".equals( element.getName() ) ) {
				AnnotationDescriptor ad = new AnnotationDescriptor( Transient.class );
				return AnnotationFactory.create( ad );
			}
		}
		if ( elementsForProperty.size() == 0 && defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( Transient.class );
		}
		else {
			return null;
		}
	}
