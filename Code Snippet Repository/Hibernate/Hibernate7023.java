	@Test
	public void testOrderColumnNoAttributes() throws Exception {
		reader = getReader( Entity2.class, "field1", "element-collection.orm3.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( OrderBy.class );
		assertAnnotationPresent( OrderColumn.class );
		OrderColumn orderColumnAnno = reader.getAnnotation( OrderColumn.class );
		assertEquals( "", orderColumnAnno.columnDefinition() );
		assertEquals( "", orderColumnAnno.name() );
		assertTrue( orderColumnAnno.insertable() );
		assertTrue( orderColumnAnno.nullable() );
		assertTrue( orderColumnAnno.updatable() );
	}
