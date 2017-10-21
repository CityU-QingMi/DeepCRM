	private Column getColumnByName(Table table, String columnName) {
		Iterator<Column> columnIterator = table.getColumnIterator();
		while ( columnIterator.hasNext() ) {
			Column column = columnIterator.next();
			if ( columnName.equals( column.getName() ) ) {
				return column;
			}
		}
		return null;
	}
