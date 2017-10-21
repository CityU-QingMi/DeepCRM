	private void determineDefaultAccessTypeAndMetaCompleteness() {
		for ( EntityMappings mappings : entityMappings ) {
			PersistenceUnitMetadata meta = mappings.getPersistenceUnitMetadata();
			if ( meta != null ) {
				if ( meta.getXmlMappingMetadataComplete() != null ) {
					context.mappingDocumentFullyXmlConfigured( true );
				}
				else {
					context.mappingDocumentFullyXmlConfigured( false );
				}

				PersistenceUnitDefaults persistenceUnitDefaults = meta.getPersistenceUnitDefaults();
				if ( persistenceUnitDefaults != null ) {
					org.hibernate.jpamodelgen.xml.jaxb.AccessType xmlAccessType = persistenceUnitDefaults.getAccess();
					if ( xmlAccessType != null ) {
						context.setPersistenceUnitDefaultAccessType( mapXmlAccessTypeToJpaAccessType( xmlAccessType ) );
					}
				}
			}
			else {
				context.mappingDocumentFullyXmlConfigured( false );
			}
		}
	}
