	private void addConstraintToColumn(final String columnName ) {
		Column column = table.getColumn(
				new Column(
						buildingContext.getMetadataCollector().getPhysicalColumnName( table, columnName )
				)
		);
		if ( column == null ) {
			throw new AnnotationException(
					"@Index references a unknown column: " + columnName
			);
		}
		if ( unique ) {
			table.getOrCreateUniqueKey( indexName ).addColumn( column );
		}
		else {
			table.getOrCreateIndex( indexName ).addColumn( column );
		}
	}
