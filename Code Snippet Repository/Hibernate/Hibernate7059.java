	@Test
	public void testMapKeyColumnNoAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "one-to-many.orm12.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		MapKeyColumn keyColAnno = reader.getAnnotation( MapKeyColumn.class );
		assertEquals( "", keyColAnno.columnDefinition() );
		assertEquals( "", keyColAnno.name() );
		assertEquals( "", keyColAnno.table() );
		assertFalse( keyColAnno.nullable() );
		assertTrue( keyColAnno.insertable() );
		assertFalse( keyColAnno.unique() );
		assertTrue( keyColAnno.updatable() );
		assertEquals( 255, keyColAnno.length() );
		assertEquals( 0, keyColAnno.precision() );
		assertEquals( 0, keyColAnno.scale() );
	}
