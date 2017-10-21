	private Document generateDefaultRevisionInfoXmlMapping() {
		final Document document = globalCfg.getEnversService().getXmlHelper().getDocumentFactory().createDocument();

		final Element classMapping = MetadataTools.createEntity(
				document,
				new AuditTableData( null, null, globalCfg.getDefaultSchemaName(), globalCfg.getDefaultCatalogName() ),
				null,
				null
		);

		classMapping.addAttribute( "name", revisionInfoEntityName );
		classMapping.addAttribute( "table", "REVINFO" );

		final Element idProperty = MetadataTools.addNativelyGeneratedId(
				classMapping,
				revisionInfoIdData.getName(),
				revisionPropType,
				globalCfg.isUseRevisionEntityWithNativeId()
		);
		MetadataTools.addColumn( idProperty, "REV", null, null, null, null, null, null, false );

		final Element timestampProperty = MetadataTools.addProperty(
				classMapping,
				revisionInfoTimestampData.getName(),
				revisionInfoTimestampType.getName(),
				true,
				false
		);
		MetadataTools.addColumn( timestampProperty, "REVTSTMP", null, null, null, null, null, null, false );

		if ( globalCfg.isTrackEntitiesChangedInRevision() ) {
			generateEntityNamesTrackingTableMapping(
					classMapping,
					"modifiedEntityNames",
					globalCfg.getDefaultSchemaName(),
					globalCfg.getDefaultCatalogName(),
					"REVCHANGES",
					"REV",
					"ENTITYNAME",
					"string"
			);
		}

		return document;
	}
