	@Override
	public AnnotationMetaAttribute visitArray(ArrayType t, Element element) {
		// METAGEN-2 - For now we handle arrays as SingularAttribute
		// The code below is an attempt to be closer to the spec and only allow byte[], Byte[], char[] and Character[]
//			AnnotationMetaSingleAttribute attribute = null;
//			TypeMirror componentMirror = t.getComponentType();
//			if ( TypeKind.CHAR.equals( componentMirror.getKind() )
//					|| TypeKind.BYTE.equals( componentMirror.getKind() ) ) {
//				attribute = new AnnotationMetaSingleAttribute( entity, element, TypeUtils.toTypeString( t ) );
//			}
//			else if ( TypeKind.DECLARED.equals( componentMirror.getKind() ) ) {
//				TypeElement componentElement = ( TypeElement ) context.getProcessingEnvironment()
//						.getTypeUtils()
//						.asElement( componentMirror );
//				if ( BASIC_ARRAY_TYPES.contains( componentElement.getQualifiedName().toString() ) ) {
//					attribute = new AnnotationMetaSingleAttribute( entity, element, TypeUtils.toTypeString( t ) );
//				}
//			}
//			return attribute;
		return new AnnotationMetaSingleAttribute( entity, element, TypeUtils.toTypeString( t ) );
	}
