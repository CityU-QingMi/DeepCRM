	@Test
	public void testMapKeyColumnAllAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "many-to-many.orm13.xml" );
		assertAnnotationPresent( ManyToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		MapKeyColumn keyColAnno = reader.getAnnotation( MapKeyColumn.class );
		assertEquals( "int", keyColAnno.columnDefinition() );
		assertEquals( "col1", keyColAnno.name() );
		assertEquals( "table1", keyColAnno.table() );
		assertTrue( keyColAnno.nullable() );
		assertFalse( keyColAnno.insertable() );
		assertTrue( keyColAnno.unique() );
		assertFalse( keyColAnno.updatable() );
		assertEquals( 50, keyColAnno.length() );
		assertEquals( 2, keyColAnno.precision() );
		assertEquals( 1, keyColAnno.scale() );
	}
