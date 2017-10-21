	public static Object getAnnotationValue(AnnotationMirror annotationMirror, String parameterValue) {
		assert annotationMirror != null;
		assert parameterValue != null;

		Object returnValue = null;
		for ( Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : annotationMirror.getElementValues()
				.entrySet() ) {
			if ( parameterValue.equals( entry.getKey().getSimpleName().toString() ) ) {
				returnValue = entry.getValue().getValue();
				break;
			}
		}
		return returnValue;
	}
