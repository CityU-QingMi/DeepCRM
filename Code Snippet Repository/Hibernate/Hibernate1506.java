	private Embeddable getEmbeddable(Element tree, XMLContext.Default defaults) {
		if ( tree == null ) {
			return defaults.canUseJavaAnnotations() ? getPhysicalAnnotation( Embeddable.class ) : null;
		}
		else {
			if ( "embeddable".equals( tree.getName() ) ) {
				AnnotationDescriptor entity = new AnnotationDescriptor( Embeddable.class );
				return AnnotationFactory.create( entity );
			}
			else {
				return null; //this is not an entity
			}
		}
	}
