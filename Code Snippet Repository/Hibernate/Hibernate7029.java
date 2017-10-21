	@Test
	public void testMapKeyEnumerated() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm9.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertEquals(
				EnumType.STRING, reader.getAnnotation(
				MapKeyEnumerated.class
		).value()
		);
	}
