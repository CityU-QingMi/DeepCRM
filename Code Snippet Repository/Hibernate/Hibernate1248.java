	private static void bindAnyMetaDef(AnyMetaDef defAnn, MetadataBuildingContext context) {
		if ( isEmptyAnnotationValue( defAnn.name() ) ) {
			//don't map not named definitions
			return;
		}
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Binding Any Meta definition: %s", defAnn.name() );
		}
		context.getMetadataCollector().addAnyMetaDef( defAnn );
	}
