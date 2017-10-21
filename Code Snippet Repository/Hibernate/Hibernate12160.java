	private AnnotationMetaAttribute createAnnotationMetaAttributeForMap(DeclaredType declaredType, Element element, String collection, String targetEntity) {
		String keyType;
		if ( TypeUtils.containsAnnotation( element, Constants.MAP_KEY_CLASS ) ) {
			TypeMirror typeMirror = (TypeMirror) TypeUtils.getAnnotationValue(
					TypeUtils.getAnnotationMirror(
							element, Constants.MAP_KEY_CLASS
					), TypeUtils.DEFAULT_ANNOTATION_PARAMETER_NAME
			);
			keyType = typeMirror.toString();
		}
		else {
			keyType = TypeUtils.getKeyType( declaredType, context );
		}
		return new AnnotationMetaMap(
				entity,
				element,
				collection,
				keyType,
				getElementType( declaredType, targetEntity )
		);
	}
