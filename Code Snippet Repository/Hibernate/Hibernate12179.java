	private static void updateEmbeddableAccessType(TypeElement element, Context context, AccessType defaultAccessType) {
		List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn( element.getEnclosedElements() );
		for ( Element field : fieldsOfClass ) {
			updateEmbeddableAccessTypeForMember( context, defaultAccessType, field );
		}

		List<? extends Element> methodOfClass = ElementFilter.methodsIn( element.getEnclosedElements() );
		for ( Element method : methodOfClass ) {
			updateEmbeddableAccessTypeForMember( context, defaultAccessType, method );
		}
	}
