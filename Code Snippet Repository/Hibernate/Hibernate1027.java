	private static TypeDescription.Generic target(FieldDescription persistentField) {
		AnnotationDescription.Loadable<Access> access = persistentField.getDeclaringType().asErasure().getDeclaredAnnotations().ofType( Access.class );
		if ( access != null && access.loadSilent().value() == AccessType.FIELD ) {
			return persistentField.getType();
		}
		else {
			MethodDescription getter = EnhancerImpl.getterOf( persistentField );
			if ( getter == null ) {
				return persistentField.getType();
			}
			else {
				return getter.getReturnType();
			}
		}
	}
