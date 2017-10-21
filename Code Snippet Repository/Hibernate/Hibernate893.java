	public InLineViewSourceImpl(
			MappingDocument mappingDocument,
			String schemaName,
			String catalogName,
			String selectStatement, 
			String logicalName,
			String comment) {
		super( mappingDocument );
		this.schemaName = determineSchemaName( mappingDocument, schemaName );
		this.catalogName = determineCatalogName( mappingDocument, catalogName );
		this.selectStatement = selectStatement;
		this.logicalName = logicalName;
		this.comment = comment;
	}
