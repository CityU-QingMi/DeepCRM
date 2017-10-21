	@Test
	public void testAllAttributes() throws Exception {
		reader = getReader( Entity1.class, "field1", "many-to-one.orm6.xml" );
		assertAnnotationPresent( ManyToOne.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinTable.class );
		assertAnnotationPresent( Id.class );
		assertAnnotationPresent( MapsId.class );
		assertAnnotationPresent( Access.class );
		ManyToOne relAnno = reader.getAnnotation( ManyToOne.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.LAZY, relAnno.fetch() );
		assertFalse( relAnno.optional() );
		assertEquals( Entity3.class, relAnno.targetEntity() );
		assertEquals( "col1", reader.getAnnotation( MapsId.class ).value() );
		assertEquals(
				AccessType.PROPERTY, reader.getAnnotation( Access.class )
				.value()
		);
	}
