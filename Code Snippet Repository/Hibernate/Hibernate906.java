	private void handlePropertyReference(
			MappingDocument mappingDocument,
			String referencedEntityName,
			String referencedPropertyName,
			boolean isUnique,
			String sourceElementSynopsis) {
		PersistentClass entityBinding = mappingDocument.getMetadataCollector().getEntityBinding( referencedEntityName );
		if ( entityBinding == null ) {
			// entity may just not have been processed yet - set up a delayed handler
			registerDelayedPropertyReferenceHandler(
					new DelayedPropertyReferenceHandlerImpl(
							referencedEntityName,
							referencedPropertyName,
							isUnique,
							sourceElementSynopsis,
							mappingDocument.getOrigin()
					),
					mappingDocument
			);
		}
		else {
			Property propertyBinding = entityBinding.getReferencedProperty( referencedPropertyName );
			if ( propertyBinding == null ) {
				// attribute may just not have been processed yet - set up a delayed handler
				registerDelayedPropertyReferenceHandler(
						new DelayedPropertyReferenceHandlerImpl(
								referencedEntityName,
								referencedPropertyName,
								isUnique,
								sourceElementSynopsis,
								mappingDocument.getOrigin()
						),
						mappingDocument
				);
			}
			else {
				log.tracef(
						"Property [%s.%s] referenced by property-ref [%s] was available - no need for delayed handling",
						referencedEntityName,
						referencedPropertyName,
						sourceElementSynopsis
				);
				if ( isUnique ) {
					( (SimpleValue) propertyBinding.getValue() ).setAlternateUniqueKey( true );
				}
			}
		}
	}
