	@SuppressWarnings("")
	private List<Identifier> extractColumnNames(List columns) {
		if ( columns == null || columns.isEmpty() ) {
			return Collections.emptyList();
		}

		final List<Identifier> columnNames = CollectionHelper.arrayList( columns.size() );
		for ( Column column : (List<Column>) columns ) {
			columnNames.add( getDatabase().toIdentifier( column.getQuotedName() ) );
		}
		return columnNames;

	}
