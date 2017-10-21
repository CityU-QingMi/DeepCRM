	private static void addAnnotations(FieldInfo fieldInfo, Class<?>[] annotations) {
		AnnotationsAttribute annotationsAttribute = (AnnotationsAttribute) fieldInfo.getAttribute( AnnotationsAttribute.visibleTag );
		if ( annotationsAttribute == null ) {
			annotationsAttribute = new AnnotationsAttribute( fieldInfo.getConstPool(), AnnotationsAttribute.visibleTag );
			fieldInfo.addAttribute( annotationsAttribute );
		}
		for (Class<?> annotation : annotations) {
			annotationsAttribute.addAnnotation( new Annotation( annotation.getName(), fieldInfo.getConstPool() ) );
		}
	}
