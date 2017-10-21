	public void linkWithValue(SimpleValue value) {
		if ( formula != null ) {
			value.addFormula( formula );
		}
		else {
			getMappingColumn().setValue( value );
			value.addColumn( getMappingColumn(), insertable, updatable );
			value.getTable().addColumn( getMappingColumn() );
			addColumnBinding( value );
			table = value.getTable();
		}
	}
