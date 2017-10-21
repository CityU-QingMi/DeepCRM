	public TableInformationImpl(
			InformationExtractor extractor,
			IdentifierHelper identifierHelper,
			QualifiedTableName tableName,
			boolean physicalTable,
			String comment ) {
		this.extractor = extractor;
		this.identifierHelper = identifierHelper;
		this.tableName = tableName;
		this.physicalTable = physicalTable;
		this.comment = comment;
	}
