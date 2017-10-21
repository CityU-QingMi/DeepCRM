	@Test
	public void testTemporal() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm18.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( Temporal.class );
		assertAnnotationNotPresent( Enumerated.class );
		assertAnnotationNotPresent( Lob.class );
		assertEquals(
				TemporalType.DATE, reader.getAnnotation(
				Temporal.class
		).value()
		);
	}
