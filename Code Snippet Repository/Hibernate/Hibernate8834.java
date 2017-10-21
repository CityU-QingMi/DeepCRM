	protected void checkDefaultJoinTableAndAllColumnNames(
			Metadata metadata,
			Class<?> ownerEntityClass,
			String ownerCollectionPropertyName,
			String expectedCollectionTableName,
			String ownerForeignKeyNameExpected,
			String[] columnNames) {
		final org.hibernate.mapping.Collection collection = metadata.getCollectionBinding( ownerEntityClass.getName() + '.' + ownerCollectionPropertyName );

		final org.hibernate.mapping.Table table = collection.getCollectionTable();
		assertEquals( expectedCollectionTableName, table.getName() );

		// The default owner and inverse join columns can only be computed if they have PK with 1 column.
		assertEquals( 1, collection.getOwner().getKey().getColumnSpan() );
		assertEquals(
			ownerForeignKeyNameExpected,
			collection.getKey().getColumnIterator().next().getText()
		);

		int columnNumber = table.getColumnSpan();
		for ( int i = 0; i < columnNumber; i++ ) {
			assertEquals( columnNames[i], table.getColumn( i + 1 ).getName());
		}
	}
