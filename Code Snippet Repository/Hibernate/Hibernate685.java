	private List<Identifier> toIdentifiers(String[] names) {
		if ( names == null ) {
			return Collections.emptyList();
		}

		final List<Identifier> columnNames = CollectionHelper.arrayList( names.length );
		for ( String name : names ) {
			columnNames.add( getDatabase().toIdentifier( name ) );
		}
		return columnNames;
	}
