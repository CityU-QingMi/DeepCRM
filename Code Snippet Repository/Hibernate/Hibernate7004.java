	@Test
	public void testNoChildren() throws Exception {
		reader = getReader( Entity2.class, "field1", "element-collection.orm1.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationNotPresent( OrderBy.class );
		assertAnnotationNotPresent( OrderColumn.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertAnnotationNotPresent( Column.class );
		assertAnnotationNotPresent( Temporal.class );
		assertAnnotationNotPresent( Enumerated.class );
		assertAnnotationNotPresent( Lob.class );
		assertAnnotationNotPresent( AttributeOverride.class );
		assertAnnotationNotPresent( AttributeOverrides.class );
		assertAnnotationNotPresent( AssociationOverride.class );
		assertAnnotationNotPresent( AssociationOverrides.class );
		assertAnnotationNotPresent( CollectionTable.class );
		assertAnnotationNotPresent( Access.class );
		ElementCollection relAnno = reader.getAnnotation( ElementCollection.class );
		assertEquals( FetchType.LAZY, relAnno.fetch() );
		assertEquals( void.class, relAnno.targetClass() );
	}
