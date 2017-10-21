	@Override
	public String getLogicalColumnName(Table table, Identifier physicalName) throws MappingException {
		final String physicalNameString = physicalName.render( getDatabase().getJdbcEnvironment().getDialect() );
		Identifier logicalName = null;

		Table currentTable = table;
		while ( currentTable != null ) {
			final TableColumnNameBinding binding = columnNameBindingByTableMap.get( currentTable );

			if ( binding != null ) {
				logicalName = binding.physicalToLogical.get( physicalNameString );
				if ( logicalName != null ) {
					break;
				}
			}

			if ( DenormalizedTable.class.isInstance( currentTable ) ) {
				currentTable = ( (DenormalizedTable) currentTable ).getIncludedTable();
			}
			else {
				currentTable = null;
			}
		}

		if ( logicalName == null ) {
			throw new MappingException(
					"Unable to find column with physical name " + physicalNameString + " in table " + table.getName()
			);
		}
		return logicalName.render();
	}
