	@Test
	public void testMixedAttributeOverrides() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm23.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( AttributeOverride.class );
		assertAnnotationPresent( AttributeOverrides.class );
		AttributeOverrides overridesAnno = reader
				.getAnnotation( AttributeOverrides.class );
		AttributeOverride[] overrides = overridesAnno.value();
		assertEquals( 2, overrides.length );
		assertEquals( "field1", overrides[0].name() );
		assertEquals( "col1", overrides[0].column().name() );
		assertEquals( "field2", overrides[1].name() );
		assertEquals( "col2", overrides[1].column().name() );
	}
