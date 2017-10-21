	@Test
	public void testSecondaryTableIndex(){
		PersistentClass entity = metadata().getEntityBinding( Car.class.getName() );

		Join join = (Join)entity.getJoinIterator().next();
		Iterator<Index> itr = join.getTable().getIndexIterator();
		assertTrue( itr.hasNext() );
		Index index = itr.next();
		assertFalse( itr.hasNext() );
		assertTrue( "index name is not generated", StringHelper.isNotEmpty( index.getName() ) );
		assertEquals( 2, index.getColumnSpan() );
		Iterator<Column> columnIterator = index.getColumnIterator();
		Column column = columnIterator.next();
		assertEquals( "dealer_name", column.getName() );
		column = columnIterator.next();
		assertEquals( "rate", column.getName() );
		assertSame( join.getTable(), index.getTable() );

	}
