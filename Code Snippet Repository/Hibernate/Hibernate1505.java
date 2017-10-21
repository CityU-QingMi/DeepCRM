	private MappedSuperclass getMappedSuperclass(Element tree, XMLContext.Default defaults) {
		if ( tree == null ) {
			return defaults.canUseJavaAnnotations() ? getPhysicalAnnotation( MappedSuperclass.class ) : null;
		}
		else {
			if ( "mapped-superclass".equals( tree.getName() ) ) {
				AnnotationDescriptor entity = new AnnotationDescriptor( MappedSuperclass.class );
				return AnnotationFactory.create( entity );
			}
			else {
				return null; //this is not an entity
			}
		}
	}
