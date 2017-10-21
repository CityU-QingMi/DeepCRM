	@Test
	public void testSingleAssociationOverride() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm24.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( AssociationOverride.class );
		assertAnnotationPresent( AssociationOverrides.class );
		AssociationOverrides overridesAnno = reader.getAnnotation( AssociationOverrides.class );
		AssociationOverride[] overrides = overridesAnno.value();
		assertEquals( 1, overrides.length );
		assertEquals( "association1", overrides[0].name() );
		assertEquals( 0, overrides[0].joinColumns().length );
		assertEquals( "", overrides[0].joinTable().name() );
	}
