	protected void validateTable(
			Table table,
			TableInformation tableInformation,
			Metadata metadata,
			ExecutionOptions options,
			Dialect dialect) {
		if ( tableInformation == null ) {
			throw new SchemaManagementException(
					String.format(
							"Schema-validation: missing table [%s]",
							table.getQualifiedTableName().toString()
					)
			);
		}

		final Iterator selectableItr = table.getColumnIterator();
		while ( selectableItr.hasNext() ) {
			final Selectable selectable = (Selectable) selectableItr.next();
			if ( Column.class.isInstance( selectable ) ) {
				final Column column = (Column) selectable;
				final ColumnInformation existingColumn = tableInformation.getColumn( Identifier.toIdentifier( column.getQuotedName() ) );
				if ( existingColumn == null ) {
					throw new SchemaManagementException(
							String.format(
									"Schema-validation: missing column [%s] in table [%s]",
									column.getName(),
									table.getQualifiedTableName()
							)
					);
				}
				validateColumnType( table, column, existingColumn, metadata, options, dialect );
			}
		}
	}
