	public String sqlConstraintString(
			Dialect dialect,
			String constraintName,
			String defaultCatalog,
			String defaultSchema) {
		String[] columnNames = new String[getColumnSpan()];
		String[] referencedColumnNames = new String[getColumnSpan()];

		final Iterator<Column> referencedColumnItr;
		if ( isReferenceToPrimaryKey() ) {
			referencedColumnItr = referencedTable.getPrimaryKey().getColumnIterator();
		}
		else {
			referencedColumnItr = referencedColumns.iterator();
		}

		Iterator columnItr = getColumnIterator();
		int i = 0;
		while ( columnItr.hasNext() ) {
			columnNames[i] = ( (Column) columnItr.next() ).getQuotedName( dialect );
			referencedColumnNames[i] = referencedColumnItr.next().getQuotedName( dialect );
			i++;
		}

		final String result = keyDefinition != null ?
				dialect.getAddForeignKeyConstraintString(
						constraintName,
						keyDefinition
				) :
				dialect.getAddForeignKeyConstraintString(
						constraintName,
						columnNames,
						referencedTable.getQualifiedName(
								dialect,
								defaultCatalog,
								defaultSchema
						),
						referencedColumnNames,
						isReferenceToPrimaryKey()
				);
		
		return cascadeDeleteEnabled && dialect.supportsCascadeDelete()
				? result + " on delete cascade"
				: result;
	}
