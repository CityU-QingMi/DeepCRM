	private Entity getEntity(Element tree, XMLContext.Default defaults) {
		if ( tree == null ) {
			return defaults.canUseJavaAnnotations() ? getPhysicalAnnotation( Entity.class ) : null;
		}
		else {
			if ( "entity".equals( tree.getName() ) ) {
				AnnotationDescriptor entity = new AnnotationDescriptor( Entity.class );
				copyStringAttribute( entity, tree, "name", false );
				if ( defaults.canUseJavaAnnotations()
						&& StringHelper.isEmpty( (String) entity.valueOf( "name" ) ) ) {
					Entity javaAnn = getPhysicalAnnotation( Entity.class );
					if ( javaAnn != null ) {
						entity.setValue( "name", javaAnn.name() );
					}
				}
				return AnnotationFactory.create( entity );
			}
			else {
				return null; //this is not an entity
			}
		}
	}
