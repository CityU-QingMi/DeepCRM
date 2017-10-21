	@Override
	public void addColumnNameBinding(Table table, Identifier logicalName, Column column) throws DuplicateMappingException {
		TableColumnNameBinding binding = null;

		if ( columnNameBindingByTableMap == null ) {
			columnNameBindingByTableMap = new HashMap<Table, TableColumnNameBinding>();
		}
		else {
			binding = columnNameBindingByTableMap.get( table );
		}

		if ( binding == null ) {
			binding = new TableColumnNameBinding( table.getName() );
			columnNameBindingByTableMap.put( table, binding );
		}

		binding.addBinding( logicalName, column );
	}
