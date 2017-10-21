	private List<Identifier> toIdentifiers(List<String> names) {
		if ( names == null || names.isEmpty() ) {
			return Collections.emptyList();
		}

		final List<Identifier> columnNames = CollectionHelper.arrayList( names.size() );
		for ( String name : names ) {
			columnNames.add( getDatabase().toIdentifier( name ) );
		}
		return columnNames;
	}
