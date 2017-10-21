	@Test
	public void testAllAttributes() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm11.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationPresent( MapsId.class );
		assertAnnotationPresent( Id.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumns.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		assertAnnotationPresent( Access.class );
		OneToOne relAnno = reader.getAnnotation( OneToOne.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.LAZY, relAnno.fetch() );
		assertEquals( "field2", relAnno.mappedBy() );
		assertFalse( relAnno.optional() );
		assertTrue( relAnno.orphanRemoval() );
		assertEquals( Entity3.class, relAnno.targetEntity() );
		assertEquals(
				AccessType.PROPERTY, reader.getAnnotation( Access.class )
				.value()
		);
		assertEquals(
				"field3", reader.getAnnotation( MapsId.class )
				.value()
		);
	}
