	private static void bindTypeDefs(XAnnotatedElement annotatedElement, MetadataBuildingContext context) {
		TypeDef defAnn = annotatedElement.getAnnotation( TypeDef.class );
		TypeDefs defsAnn = annotatedElement.getAnnotation( TypeDefs.class );
		if ( defAnn != null ) {
			bindTypeDef( defAnn, context );
		}
		if ( defsAnn != null ) {
			for ( TypeDef def : defsAnn.value() ) {
				bindTypeDef( def, context );
			}
		}
	}
