	public String generateHashedFkName(
			String prefix,
			Identifier tableName,
			Identifier referencedTableName,
			List<Identifier> columnNames) {
		final Identifier[] columnNamesArray;
		if ( columnNames == null || columnNames.isEmpty() ) {
			columnNamesArray = new Identifier[0];
		}
		else {
			columnNamesArray = columnNames.toArray( new Identifier[ columnNames.size() ] );
		}

		return generateHashedFkName(
				prefix,
				tableName,
				referencedTableName,
				columnNamesArray
		);
	}
