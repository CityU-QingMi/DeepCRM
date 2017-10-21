	private SequenceGenerator getSequenceGenerator(Element tree, XMLContext.Default defaults) {
		Element element = tree != null ? tree.element( annotationToXml.get( SequenceGenerator.class ) ) : null;
		if ( element != null ) {
			return buildSequenceGeneratorAnnotation( element );
		}
		else if ( defaults.canUseJavaAnnotations() ) {
			return getPhysicalAnnotation( SequenceGenerator.class );
		}
		else {
			return null;
		}
	}
