	@Test
	public void testSingleMapKeyJoinColumn() throws Exception {
		reader = getReader( Entity3.class, "field1", "many-to-many.orm14.xml" );
		assertAnnotationPresent( ManyToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		MapKeyJoinColumns joinColumnsAnno = reader
				.getAnnotation( MapKeyJoinColumns.class );
		MapKeyJoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 1, joinColumns.length );
		assertEquals( "col1", joinColumns[0].name() );
	}
