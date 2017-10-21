	private static Element createEntityCommon(
			Document document,
			String type,
			AuditTableData auditTableData,
			String discriminatorValue,
			Boolean isAbstract) {
		final Element hibernateMapping = document.addElement( "hibernate-mapping" );
		hibernateMapping.addAttribute( "auto-import", "false" );

		final Element classMapping = hibernateMapping.addElement( type );

		if ( auditTableData.getAuditEntityName() != null ) {
			classMapping.addAttribute( "entity-name", auditTableData.getAuditEntityName() );
		}

		if ( discriminatorValue != null ) {
			classMapping.addAttribute( "discriminator-value", discriminatorValue );
		}

		if ( !StringTools.isEmpty( auditTableData.getAuditTableName() ) ) {
			classMapping.addAttribute( "table", auditTableData.getAuditTableName() );
		}

		if ( !StringTools.isEmpty( auditTableData.getSchema() ) ) {
			classMapping.addAttribute( "schema", auditTableData.getSchema() );
		}

		if ( !StringTools.isEmpty( auditTableData.getCatalog() ) ) {
			classMapping.addAttribute( "catalog", auditTableData.getCatalog() );
		}

		if ( isAbstract != null ) {
			classMapping.addAttribute( "abstract", isAbstract.toString() );
		}

		return classMapping;
	}
