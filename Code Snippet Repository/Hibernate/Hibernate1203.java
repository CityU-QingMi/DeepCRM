	private static boolean isEntityClassType(XClass clazzToProcess, AnnotatedClassType classType) {
		if ( AnnotatedClassType.EMBEDDABLE_SUPERCLASS.equals( classType ) //will be processed by their subentities
				|| AnnotatedClassType.NONE.equals( classType ) //to be ignored
				|| AnnotatedClassType.EMBEDDABLE.equals( classType ) //allow embeddable element declaration
				) {
			if ( AnnotatedClassType.NONE.equals( classType )
					&& clazzToProcess.isAnnotationPresent( org.hibernate.annotations.Entity.class ) ) {
				LOG.missingEntityAnnotation( clazzToProcess.getName() );
			}
			return false;
		}

		if ( !classType.equals( AnnotatedClassType.ENTITY ) ) {
			throw new AnnotationException(
					"Annotated class should have a @javax.persistence.Entity, @javax.persistence.Embeddable or @javax.persistence.EmbeddedSuperclass annotation: " + clazzToProcess
							.getName()
			);
		}

		return true;
	}
