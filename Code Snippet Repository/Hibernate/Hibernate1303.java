	public void linkValueUsingDefaultColumnNaming(
			Column referencedColumn,
			PersistentClass referencedEntity,
			SimpleValue value) {
		String logicalReferencedColumn = getBuildingContext().getMetadataCollector().getLogicalColumnName(
				referencedEntity.getTable(),
				referencedColumn.getQuotedName()
		);
		String columnName = buildDefaultColumnName( referencedEntity, logicalReferencedColumn );

		//yuk side effect on an implicit column
		setLogicalColumnName( columnName );
		setReferencedColumn( logicalReferencedColumn );
		initMappingColumn(
				columnName,
				null, referencedColumn.getLength(),
				referencedColumn.getPrecision(),
				referencedColumn.getScale(),
				getMappingColumn() != null ? getMappingColumn().isNullable() : false,
				referencedColumn.getSqlType(),
				getMappingColumn() != null ? getMappingColumn().isUnique() : false,
				false
		);
		linkWithValue( value );
	}
