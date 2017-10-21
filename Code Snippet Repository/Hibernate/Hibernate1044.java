	static <T extends Annotation> AnnotationDescription.Loadable<T> getAnnotation(FieldDescription fieldDescription, Class<T> type) {
		AnnotationDescription.Loadable<Access> access = fieldDescription.getDeclaringType().asErasure().getDeclaredAnnotations().ofType( Access.class );
		if ( access != null && access.loadSilent().value() == AccessType.PROPERTY ) {
			MethodDescription getter = getterOf( fieldDescription );
			if ( getter == null ) {
				return fieldDescription.getDeclaredAnnotations().ofType( type );
			}
			else {
				return getter.getDeclaredAnnotations().ofType( type );
			}
		}
		else if ( access != null && access.loadSilent().value() == AccessType.FIELD ) {
			return fieldDescription.getDeclaredAnnotations().ofType( type );
		}
		else {
			MethodDescription getter = getterOf( fieldDescription );
			if ( getter != null ) {
				AnnotationDescription.Loadable<T> annotationDescription = getter.getDeclaredAnnotations().ofType( type );
				if ( annotationDescription != null ) {
					return annotationDescription;
				}
			}
			return fieldDescription.getDeclaredAnnotations().ofType( type );
		}
	}
