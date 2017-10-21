	@Test
	public void testMapKeyNoAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "many-to-many.orm5.xml" );
		assertAnnotationPresent( ManyToMany.class );
		assertAnnotationPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertEquals( "", reader.getAnnotation( MapKey.class ).name() );
	}
