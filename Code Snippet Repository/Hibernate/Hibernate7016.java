	@Test
	public void testSingleAttributeOverride() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm21.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( AttributeOverride.class );
		assertAnnotationPresent( AttributeOverrides.class );
		AttributeOverrides overridesAnno = reader
				.getAnnotation( AttributeOverrides.class );
		AttributeOverride[] overrides = overridesAnno.value();
		assertEquals( 1, overrides.length );
		assertEquals( "field1", overrides[0].name() );
		assertEquals( "col1", overrides[0].column().name() );
	}
