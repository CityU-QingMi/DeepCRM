	@Test
	public void testOrderBy() throws Exception {
		reader = getReader( Entity2.class, "field1", "element-collection.orm2.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( OrderBy.class );
		assertAnnotationNotPresent( OrderColumn.class );
		assertEquals(
				"col1 ASC, col2 DESC", reader.getAnnotation( OrderBy.class )
				.value()
		);
	}
