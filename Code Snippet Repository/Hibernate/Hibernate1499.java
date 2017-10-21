	private DiscriminatorValue getDiscriminatorValue(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( "discriminator-value" ) : null;
		if ( element != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( DiscriminatorValue.class );
			copyStringElement( element, ad, "value" );
			return AnnotationFactory.create( ad );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( DiscriminatorValue.class );
		}
		else {
			return null;
		}
	}
