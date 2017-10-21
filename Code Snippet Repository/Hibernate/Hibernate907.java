	private void registerDelayedPropertyReferenceHandler(
			DelayedPropertyReferenceHandlerImpl handler,
			MetadataBuildingContext buildingContext) {
		log.tracef(
				"Property [%s.%s] referenced by property-ref [%s] was not yet available - creating delayed handler",
				handler.referencedEntityName,
				handler.referencedPropertyName,
				handler.sourceElementSynopsis
		);
		buildingContext.getMetadataCollector().addDelayedPropertyReferenceHandler( handler );
	}
