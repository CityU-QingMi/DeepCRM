	private static <T extends Annotation> T getAnnotationOrNull(CtMember ctMember, Class<T> annotation) {
		try {
			if ( ctMember.hasAnnotation( annotation ) ) {
				return annotation.cast( ctMember.getAnnotation( annotation ) );
			}
		}
		catch (ClassNotFoundException cnfe) {
			// should never happen
		}
		return null;
	}
