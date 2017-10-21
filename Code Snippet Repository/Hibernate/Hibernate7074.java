	@Test
	public void testMapKeyClass() throws Exception {
		reader = getReader( Entity3.class, "field1", "one-to-many.orm7.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertEquals(
				Entity2.class, reader.getAnnotation( MapKeyClass.class )
				.value()
		);
	}
