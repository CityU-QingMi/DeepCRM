	@Test
	public void testMapKeyAllAttributes() throws Exception {
		reader = getReader( Entity3.class, "field1", "one-to-many.orm6.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertEquals( "field2", reader.getAnnotation( MapKey.class ).name() );
	}
