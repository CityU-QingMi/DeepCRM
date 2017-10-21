	private boolean isBasicAttribute(Element element, Element returnedElement) {
		if ( TypeUtils.containsAnnotation( element, Constants.BASIC )
				|| TypeUtils.containsAnnotation( element, Constants.ONE_TO_ONE )
				|| TypeUtils.containsAnnotation( element, Constants.MANY_TO_ONE )
				|| TypeUtils.containsAnnotation( element, Constants.EMBEDDED_ID )
				|| TypeUtils.containsAnnotation( element, Constants.ID ) ) {
			return true;
		}

		// METAGEN-28
		if ( TypeUtils.getAnnotationMirror( element, ORG_HIBERNATE_ANNOTATIONS_TYPE ) != null ) {
			return true;
		}

		BasicAttributeVisitor basicVisitor = new BasicAttributeVisitor( context );
		return returnedElement.asType().accept( basicVisitor, returnedElement );
	}
