	@SuppressWarnings("")
	public static boolean isColumnPresent(String tableName, String columnName, Metadata metadata) {
		for ( Table table : metadata.collectTableMappings() ) {
			if (tableName.equals( table.getName() ) ) {
				Iterator<Column> columns = (Iterator<Column>) table.getColumnIterator();
				while ( columns.hasNext() ) {
					Column column = columns.next();
					if ( columnName.equals( column.getName() ) ) {
						return true;
					}
				}
			}
		}

		return false;
	}
