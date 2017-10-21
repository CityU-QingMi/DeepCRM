	@Test
	public void testEnumerated() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm19.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( Temporal.class );
		assertAnnotationPresent( Enumerated.class );
		assertAnnotationNotPresent( Lob.class );
		assertEquals(
				EnumType.STRING, reader.getAnnotation(
				Enumerated.class
		).value()
		);
	}
