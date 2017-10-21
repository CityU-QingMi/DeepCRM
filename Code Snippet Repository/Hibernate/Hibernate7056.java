	@Test
	public void testNoChildren() throws Exception {
		reader = getReader( Entity2.class, "field1", "one-to-many.orm1.xml" );
		assertAnnotationPresent( OneToMany.class );
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
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( Access.class );
		OneToMany relAnno = reader.getAnnotation( OneToMany.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.LAZY, relAnno.fetch() );
		assertEquals( "", relAnno.mappedBy() );
		assertFalse( relAnno.orphanRemoval() );
		assertEquals( void.class, relAnno.targetEntity() );
	}
