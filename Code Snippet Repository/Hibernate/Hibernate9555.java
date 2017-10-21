	private ColumnInformation getColumnInformation(String tableName, String columnName) {
		Name schemaName = new Name( new Identifier( "-", false ), new Identifier( "-", false ) );
		TableInformation containingTableInformation = new TableInformationImpl( null, null,
				new QualifiedTableName( schemaName, new Identifier( tableName, false ) ), false, null );
		Identifier columnIdentifier = new Identifier( columnName, false );
		int typeCode = 0;
		String typeName = null;
		int columnSize = 0;
		int decimalDigits = 0;
		TruthValue nullable = null;
		ColumnInformationImpl columnInformation = new ColumnInformationImpl( containingTableInformation, columnIdentifier, typeCode, typeName, columnSize,
				decimalDigits, nullable );
		return columnInformation;
	}
