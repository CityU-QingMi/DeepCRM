	public void parseXml() {
		Collection<String> mappingFileNames = determineMappingFileNames();

		if ( context.doLazyXmlParsing() && mappingFilesUnchanged( mappingFileNames ) ) {
			return;
		}

		loadEntityMappings( mappingFileNames );
		determineDefaultAccessTypeAndMetaCompleteness();
		determineXmlAccessTypes();
		if ( !context.isFullyXmlConfigured() ) {
			// need to take annotations into consideration, since they can override xml settings
			// we have to at least determine whether any of the xml configured entities is influenced by annotations
			determineAnnotationAccessTypes();
		}

		for ( EntityMappings mappings : entityMappings ) {
			String defaultPackageName = mappings.getPackage();
			parseEntities( mappings.getEntity(), defaultPackageName );
			parseEmbeddable( mappings.getEmbeddable(), defaultPackageName );
			parseMappedSuperClass( mappings.getMappedSuperclass(), defaultPackageName );
		}
	}
