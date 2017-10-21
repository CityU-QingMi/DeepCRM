	private static AccessType getAccessTypeInCaseElementIsRoot(TypeElement searchedElement, Context context) {
		List<? extends Element> myMembers = searchedElement.getEnclosedElements();
		for ( Element subElement : myMembers ) {
			List<? extends AnnotationMirror> entityAnnotations =
					context.getElementUtils().getAllAnnotationMirrors( subElement );
			for ( Object entityAnnotation : entityAnnotations ) {
				AnnotationMirror annotationMirror = (AnnotationMirror) entityAnnotation;
				if ( isIdAnnotation( annotationMirror ) ) {
					return getAccessTypeOfIdAnnotation( subElement );
				}
			}
		}
		return null;
	}
