	@Test
	public void testNoJoins() throws Exception {
		reader = getReader( Entity1.class, "field1", "many-to-one.orm1.xml" );
		assertAnnotationPresent( ManyToOne.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinTable.class );
		assertAnnotationNotPresent( Id.class );
		assertAnnotationNotPresent( MapsId.class );
		assertAnnotationNotPresent( Access.class );
		ManyToOne relAnno = reader.getAnnotation( ManyToOne.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.EAGER, relAnno.fetch() );
		assertTrue( relAnno.optional() );
		assertEquals( void.class, relAnno.targetEntity() );
	}
