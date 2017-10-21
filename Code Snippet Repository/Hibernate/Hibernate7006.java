	@Test
	public void testMultipleMapKeyAttributeOverrides() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm11.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertAnnotationNotPresent( AttributeOverride.class );
		assertAnnotationPresent( AttributeOverrides.class );
		AttributeOverrides overridesAnno = reader
				.getAnnotation( AttributeOverrides.class );
		AttributeOverride[] overrides = overridesAnno.value();
		assertEquals( 2, overrides.length );
		assertEquals( "field1", overrides[0].name() );
		assertEquals( "", overrides[0].column().name() );
		assertFalse( overrides[0].column().unique() );
		assertTrue( overrides[0].column().nullable() );
		assertTrue( overrides[0].column().insertable() );
		assertTrue( overrides[0].column().updatable() );
		assertEquals( "", overrides[0].column().columnDefinition() );
		assertEquals( "", overrides[0].column().table() );
		assertEquals( 255, overrides[0].column().length() );
		assertEquals( 0, overrides[0].column().precision() );
		assertEquals( 0, overrides[0].column().scale() );
		assertEquals( "field2", overrides[1].name() );
		assertEquals( "col1", overrides[1].column().name() );
		assertTrue( overrides[1].column().unique() );
		assertFalse( overrides[1].column().nullable() );
		assertFalse( overrides[1].column().insertable() );
		assertFalse( overrides[1].column().updatable() );
		assertEquals( "int", overrides[1].column().columnDefinition() );
		assertEquals( "table1", overrides[1].column().table() );
		assertEquals( 50, overrides[1].column().length() );
		assertEquals( 2, overrides[1].column().precision() );
		assertEquals( 1, overrides[1].column().scale() );
	}
