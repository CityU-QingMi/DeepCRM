	@Test
	public void testNoChildren() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm1.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationNotPresent( MapsId.class );
		assertAnnotationNotPresent( Id.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumns.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		assertAnnotationNotPresent( Access.class );
		OneToOne relAnno = reader.getAnnotation( OneToOne.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.EAGER, relAnno.fetch() );
		assertEquals( "", relAnno.mappedBy() );
		assertTrue( relAnno.optional() );
		assertFalse( relAnno.orphanRemoval() );
		assertEquals( void.class, relAnno.targetEntity() );
	}
