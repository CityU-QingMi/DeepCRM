	@Test
	public void testSingleMapKeyAttributeOverride() throws Exception {
		reader = getReader( Entity3.class, "field1", "many-to-many.orm10.xml" );
		assertAnnotationPresent( ManyToMany.class );
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
		assertEquals( 1, overrides.length );
		assertEquals( "field1", overrides[0].name() );
		assertEquals( "col1", overrides[0].column().name() );
	}
