	public static void bindAnyMetaDefs(XAnnotatedElement annotatedElement, MetadataBuildingContext context) {
		AnyMetaDef defAnn = annotatedElement.getAnnotation( AnyMetaDef.class );
		AnyMetaDefs defsAnn = annotatedElement.getAnnotation( AnyMetaDefs.class );
		boolean mustHaveName = XClass.class.isAssignableFrom( annotatedElement.getClass() )
				|| XPackage.class.isAssignableFrom( annotatedElement.getClass() );
		if ( defAnn != null ) {
			checkAnyMetaDefValidity( mustHaveName, defAnn, annotatedElement );
			bindAnyMetaDef( defAnn, context );
		}
		if ( defsAnn != null ) {
			for (AnyMetaDef def : defsAnn.value()) {
				checkAnyMetaDefValidity( mustHaveName, def, annotatedElement );
				bindAnyMetaDef( def, context );
			}
		}
	}
