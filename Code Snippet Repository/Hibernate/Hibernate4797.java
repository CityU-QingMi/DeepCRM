	@Override
	public TableInformation getTableInformation(QualifiedTableName tableName) {
		if ( tableName.getObjectName() == null ) {
			throw new IllegalArgumentException( "Passed table name cannot be null" );
		}

		return extractor.getTable(
				tableName.getCatalogName(),
				tableName.getSchemaName(),
				tableName.getTableName()
		);
	}
