	@Test
	public void testCollectionTableIndex(){
		PersistentClass entity = metadata().getEntityBinding( Car.class.getName() );
		Property property = entity.getProperty( "otherDealers" );
		Set set = (Set)property.getValue();
		Table collectionTable = set.getCollectionTable();

		Iterator<Index> itr = collectionTable.getIndexIterator();
		assertTrue( itr.hasNext() );
		Index index = itr.next();
		assertFalse( itr.hasNext() );
		assertTrue( "index name is not generated", StringHelper.isNotEmpty( index.getName() ) );
		assertEquals( 1, index.getColumnSpan() );
		Iterator<Column> columnIterator = index.getColumnIterator();
		Column column = columnIterator.next();
		assertEquals( "name", column.getName() );
		assertSame( collectionTable, index.getTable() );

	}
