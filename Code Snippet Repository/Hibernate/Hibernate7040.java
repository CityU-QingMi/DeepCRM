	@Test
	public void testAllAttributes() throws Exception {
		reader = getReader( Entity2.class, "field1", "many-to-many.orm21.xml" );
		assertAnnotationPresent( ManyToMany.class );
		assertAnnotationNotPresent( OrderBy.class );
		assertAnnotationNotPresent( OrderColumn.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		assertAnnotationPresent( Access.class );
		ManyToMany relAnno = reader.getAnnotation( ManyToMany.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.EAGER, relAnno.fetch() );
		assertEquals( "field2", relAnno.mappedBy() );
		assertEquals( Entity3.class, relAnno.targetEntity() );
		assertEquals(
				AccessType.PROPERTY, reader.getAnnotation( Access.class )
				.value()
		);
	}
