	private String getFullyQualifiedClassNameOfTargetEntity(AnnotationMirror mirror, String parameterName) {
		assert mirror != null;
		assert parameterName != null;

		String targetEntityName = null;
		Object parameterValue = TypeUtils.getAnnotationValue( mirror, parameterName );
		if ( parameterValue != null ) {
			TypeMirror parameterType = (TypeMirror) parameterValue;
			if ( !parameterType.getKind().equals( TypeKind.VOID ) ) {
				targetEntityName = parameterType.toString();
			}
		}
		return targetEntityName;
	}
