	private String getTargetEntity(List<? extends AnnotationMirror> annotations) {
		String fullyQualifiedTargetEntityName = null;
		for ( AnnotationMirror mirror : annotations ) {
			if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.ELEMENT_COLLECTION ) ) {
				fullyQualifiedTargetEntityName = getFullyQualifiedClassNameOfTargetEntity( mirror, "targetClass" );
			}
			else if ( TypeUtils.isAnnotationMirrorOfType( mirror, Constants.ONE_TO_MANY )
					|| TypeUtils.isAnnotationMirrorOfType( mirror, Constants.MANY_TO_MANY )
					|| TypeUtils.isAnnotationMirrorOfType( mirror, Constants.MANY_TO_ONE )
					|| TypeUtils.isAnnotationMirrorOfType( mirror, Constants.ONE_TO_ONE ) ) {
				fullyQualifiedTargetEntityName = getFullyQualifiedClassNameOfTargetEntity( mirror, "targetEntity" );
			}
			else if ( TypeUtils.isAnnotationMirrorOfType( mirror, ORG_HIBERNATE_ANNOTATIONS_TARGET ) ) {
				fullyQualifiedTargetEntityName = getFullyQualifiedClassNameOfTargetEntity( mirror, "value" );
			}
		}
		return fullyQualifiedTargetEntityName;
	}
