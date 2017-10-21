	public static AccessType determineAnnotationSpecifiedAccessType(Element element) {
		final AnnotationMirror accessAnnotationMirror = TypeUtils.getAnnotationMirror( element, Constants.ACCESS );
		AccessType forcedAccessType = null;
		if ( accessAnnotationMirror != null ) {
			Element accessElement = (Element) TypeUtils.getAnnotationValue(
					accessAnnotationMirror,
					DEFAULT_ANNOTATION_PARAMETER_NAME
			);
			if ( accessElement.getKind().equals( ElementKind.ENUM_CONSTANT ) ) {
				if ( accessElement.getSimpleName().toString().equals( AccessType.PROPERTY.toString() ) ) {
					forcedAccessType = AccessType.PROPERTY;
				}
				else if ( accessElement.getSimpleName().toString().equals( AccessType.FIELD.toString() ) ) {
					forcedAccessType = AccessType.FIELD;
				}
			}
		}
		return forcedAccessType;
	}
