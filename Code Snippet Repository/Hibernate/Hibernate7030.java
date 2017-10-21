	@Test
	public void testNoChildren() throws Exception {
		reader = getReader( Entity2.class, "field1", "many-to-many.orm1.xml" );
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
		assertAnnotationNotPresent( Access.class );
		ManyToMany relAnno = reader.getAnnotation( ManyToMany.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.LAZY, relAnno.fetch() );
		assertEquals( "", relAnno.mappedBy() );
		assertEquals( void.class, relAnno.targetEntity() );
	}
