	private Element createMiddleEntityXml(String auditMiddleTableName, String auditMiddleEntityName, String where) {
		final String schema = mainGenerator.getSchema(
				propertyAuditingData.getJoinTable().schema(),
				propertyValue.getCollectionTable()
		);
		final String catalog = mainGenerator.getCatalog(
				propertyAuditingData.getJoinTable().catalog(),
				propertyValue.getCollectionTable()
		);

		final Element middleEntityXml = MetadataTools.createEntity(
				xmlMappingData.newAdditionalMapping(),
				new AuditTableData( auditMiddleEntityName, auditMiddleTableName, schema, catalog ), null, null
		);
		final Element middleEntityXmlId = middleEntityXml.addElement( "composite-id" );

		// If there is a where clause on the relation, adding it to the middle entity.
		if ( where != null ) {
			middleEntityXml.addAttribute( "where", where );
		}

		middleEntityXmlId.addAttribute( "name", mainGenerator.getVerEntCfg().getOriginalIdPropName() );

		// Adding the revision number as a foreign key to the revision info entity to the composite id of the
		// middle table.
		mainGenerator.addRevisionInfoRelation( middleEntityXmlId );

		// Adding the revision type property to the entity xml.
		mainGenerator.addRevisionType(
				isElementRevisionTypeInId() ? middleEntityXmlId : middleEntityXml,
				middleEntityXml,
				isElementRevisionTypeInId()
		);

		// All other properties should also be part of the primary key of the middle entity.
		return middleEntityXmlId;
	}
