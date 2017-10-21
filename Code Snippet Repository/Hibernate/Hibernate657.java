	@Override
	public String getPhysicalColumnName(Table table, Identifier logicalName) throws MappingException {
		if ( logicalName == null ) {
			throw new MappingException( "Logical column name cannot be null" );
		}

		Table currentTable = table;
		String physicalName = null;

		while ( currentTable != null ) {
			final TableColumnNameBinding binding = columnNameBindingByTableMap.get( currentTable );
			if ( binding != null ) {
				physicalName = binding.logicalToPhysical.get( logicalName );
				if ( physicalName != null ) {
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

		if ( physicalName == null ) {
			throw new MappingException(
					"Unable to find column with logical name " + logicalName.render() + " in table " + table.getName()
			);
		}
		return physicalName;
	}
