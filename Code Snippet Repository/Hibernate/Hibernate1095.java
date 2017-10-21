	public static <T extends Annotation> T getAnnotation(CtClass ctClass, String attributeName, Class<T> annotation) {
		AccessType classAccessType = getAccessTypeOrNull( ctClass );
		CtField field = findFieldOrNull( ctClass, attributeName );
		CtMethod getter = findGetterOrNull( ctClass, attributeName );

		if ( classAccessType == AccessType.FIELD || ( field != null && getAccessTypeOrNull( field ) == AccessType.FIELD ) ) {
			return field == null ? null : getAnnotationOrNull( field, annotation );
		}
		if ( classAccessType == AccessType.PROPERTY || ( getter != null && getAccessTypeOrNull( getter ) == AccessType.PROPERTY ) ) {
			return getter == null ? null : getAnnotationOrNull( getter, annotation );
		}

		T found = ( getter == null ? null : getAnnotationOrNull( getter, annotation ) );
		if ( found == null && field != null ) {
			return getAnnotationOrNull( field, annotation );
		}
		return found;
	}
