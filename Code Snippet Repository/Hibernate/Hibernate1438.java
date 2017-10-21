	private Cacheable getCacheable(Element element, XMLContext.Default defaults){
		if ( element != null ) {
			String attValue = element.attributeValue( "cacheable" );
			if ( attValue != null ) {
				AnnotationDescriptor ad = new AnnotationDescriptor( Cacheable.class );
				ad.setValue( "value", Boolean.valueOf( attValue ) );
				return AnnotationFactory.create( ad );
			}
		}
		if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( Cacheable.class );
		}
		else {
			return null;
		}
	}
