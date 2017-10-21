	@Test
	public void testColumnAllAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm17.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( Column.class );
		Column column = reader.getAnnotation( Column.class );
		assertEquals( "col1", column.name() );
		assertTrue( column.unique() );
		assertFalse( column.nullable() );
		assertFalse( column.insertable() );
		assertFalse( column.updatable() );
		assertEquals( "int", column.columnDefinition() );
		assertEquals( "table1", column.table() );
		assertEquals( 50, column.length() );
		assertEquals( 2, column.precision() );
		assertEquals( 1, column.scale() );
	}
