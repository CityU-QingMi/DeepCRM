	@Test
	public void testJoinTableIndex(){
		PersistentClass entity = metadata().getEntityBinding( Importer.class.getName() );
		Property property = entity.getProperty( "cars" );
		Bag set = (Bag)property.getValue();
		Table collectionTable = set.getCollectionTable();

		Iterator<Index> itr = collectionTable.getIndexIterator();
		assertTrue( itr.hasNext() );
		Index index = itr.next();
		assertFalse( itr.hasNext() );
		assertTrue( "index name is not generated", StringHelper.isNotEmpty( index.getName() ) );
		assertEquals( 1, index.getColumnSpan() );
		Iterator<Column> columnIterator = index.getColumnIterator();
		Column column = columnIterator.next();
		assertEquals( "importers_id", column.getName() );
		assertSame( collectionTable, index.getTable() );
	}
