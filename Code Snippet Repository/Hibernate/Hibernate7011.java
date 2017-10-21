	@Test
	public void testColumnNoAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm16.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( Column.class );
		Column column = reader.getAnnotation( Column.class );
		assertEquals( "", column.name() );
		assertFalse( column.unique() );
		assertTrue( column.nullable() );
		assertTrue( column.insertable() );
		assertTrue( column.updatable() );
		assertEquals( "", column.columnDefinition() );
		assertEquals( "", column.table() );
		assertEquals( 255, column.length() );
		assertEquals( 0, column.precision() );
		assertEquals( 0, column.scale() );
	}
