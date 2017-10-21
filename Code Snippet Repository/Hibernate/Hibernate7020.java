	@Test
	public void testCollectionTableNoChildren() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm26.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( CollectionTable.class );
		CollectionTable tableAnno = reader.getAnnotation( CollectionTable.class );
		assertEquals( "", tableAnno.name() );
		assertEquals( "", tableAnno.catalog() );
		assertEquals( "", tableAnno.schema() );
		assertEquals( 0, tableAnno.joinColumns().length );
		assertEquals( 0, tableAnno.uniqueConstraints().length );
	}
