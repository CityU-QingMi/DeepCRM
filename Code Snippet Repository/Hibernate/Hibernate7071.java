	@Test
	public void testOrderColumnAllAttributes() throws Exception {
		reader = getReader( Entity2.class, "field1", "one-to-many.orm4.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationNotPresent( OrderBy.class );
		assertAnnotationPresent( OrderColumn.class );
		OrderColumn orderColumnAnno = reader.getAnnotation( OrderColumn.class );
		assertEquals( "int", orderColumnAnno.columnDefinition() );
		assertEquals( "col1", orderColumnAnno.name() );
		assertFalse( orderColumnAnno.insertable() );
		assertFalse( orderColumnAnno.nullable() );
		assertFalse( orderColumnAnno.updatable() );
	}
