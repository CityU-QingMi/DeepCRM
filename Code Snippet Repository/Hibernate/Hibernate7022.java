	@Test
	public void testAllAttributes() throws Exception {
		reader = getReader( Entity2.class, "field1", "element-collection.orm28.xml" );
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
		assertAnnotationPresent( Access.class );
		ElementCollection relAnno = reader.getAnnotation( ElementCollection.class );
		assertEquals( FetchType.EAGER, relAnno.fetch() );
		assertEquals( Entity3.class, relAnno.targetClass() );
		assertEquals(
				AccessType.PROPERTY, reader.getAnnotation( Access.class )
				.value()
		);
	}
