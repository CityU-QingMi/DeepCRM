	public static SequenceGenerator buildSequenceGeneratorAnnotation(Element element) {
		if ( element != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( SequenceGenerator.class );
			copyStringAttribute( ad, element, "name", false );
			copyStringAttribute( ad, element, "sequence-name", false );
			copyIntegerAttribute( ad, element, "initial-value" );
			copyIntegerAttribute( ad, element, "allocation-size" );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
