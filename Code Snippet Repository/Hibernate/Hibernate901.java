	private void handleNaturalIdBinding(
			MappingDocument mappingDocument,
			PersistentClass entityBinding,
			Property attributeBinding,
			NaturalIdMutability naturalIdMutability) {
		if ( naturalIdMutability == NaturalIdMutability.NOT_NATURAL_ID ) {
			return;
		}

		attributeBinding.setNaturalIdentifier( true );

		if ( naturalIdMutability == NaturalIdMutability.IMMUTABLE ) {
			attributeBinding.setUpdateable( false );
		}

		NaturalIdUniqueKeyBinder ukBinder = mappingDocument.getMetadataCollector().locateNaturalIdUniqueKeyBinder(
				entityBinding.getEntityName()
		);

		if ( ukBinder == null ) {
			ukBinder = new NaturalIdUniqueKeyBinderImpl( mappingDocument, entityBinding );
			mappingDocument.getMetadataCollector().registerNaturalIdUniqueKeyBinder(
					entityBinding.getEntityName(),
					ukBinder
			);
		}

		ukBinder.addAttributeBinding( attributeBinding );
	}
