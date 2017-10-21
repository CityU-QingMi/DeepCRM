	private AccessType determineDefaultAccessType() {
		for (XClass xclass = clazz; xclass != null; xclass = xclass.getSuperclass()) {
			if ( ( xclass.getSuperclass() == null || Object.class.getName().equals( xclass.getSuperclass().getName() ) )
					&& ( xclass.isAnnotationPresent( Entity.class ) || xclass.isAnnotationPresent( MappedSuperclass.class ) )
					&& xclass.isAnnotationPresent( Access.class ) ) {
				return AccessType.getAccessStrategy( xclass.getAnnotation( Access.class ).value() );
			}
		}
        // Guess from identifier.
        // FIX: Shouldn't this be determined by the first attribute (i.e., field or property) with annotations, but without an
        //      explicit Access annotation, according to JPA 2.0 spec 2.3.1: Default Access Type?
		for (XClass xclass = clazz; xclass != null && !Object.class.getName().equals(xclass.getName()); xclass = xclass.getSuperclass()) {
			if ( xclass.isAnnotationPresent( Entity.class ) || xclass.isAnnotationPresent( MappedSuperclass.class ) ) {
				for ( XProperty prop : xclass.getDeclaredProperties( AccessType.PROPERTY.getType() ) ) {
					final boolean isEmbeddedId = prop.isAnnotationPresent( EmbeddedId.class );
					if ( prop.isAnnotationPresent( Id.class ) || isEmbeddedId ) {
						return AccessType.PROPERTY;
					}
				}
				for ( XProperty prop : xclass.getDeclaredProperties( AccessType.FIELD.getType() ) ) {
					final boolean isEmbeddedId = prop.isAnnotationPresent( EmbeddedId.class );
					if ( prop.isAnnotationPresent( Id.class ) || isEmbeddedId ) {
						return AccessType.FIELD;
					}
				}
			}
		}
		throw new AnnotationException( "No identifier specified for entity: " + clazz );
	}
