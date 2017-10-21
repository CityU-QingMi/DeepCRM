	private void addConstraintToColumns(List<Column> columns) {
		if ( unique ) {
			UniqueKey uniqueKey = table.getOrCreateUniqueKey( indexName );
			for ( Column column : columns ) {
				uniqueKey.addColumn( column );
			}
		}
		else {
			Index index = table.getOrCreateIndex( indexName );
			for ( Column column : columns ) {
				index.addColumn( column );
			}
		}
	}
