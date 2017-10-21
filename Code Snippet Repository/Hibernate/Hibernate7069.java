	@Test
	public void testAllAttributes() throws Exception {
		reader = getReader( Entity2.class, "field1", "one-to-many.orm23.xml" );
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
		assertAnnotationPresent( Access.class );
		OneToMany relAnno = reader.getAnnotation( OneToMany.class );
		assertEquals( 0, relAnno.cascade().length );
		assertEquals( FetchType.EAGER, relAnno.fetch() );
		assertEquals( "field2", relAnno.mappedBy() );
		assertTrue( relAnno.orphanRemoval() );
		assertEquals( Entity3.class, relAnno.targetEntity() );
		assertEquals(
				AccessType.PROPERTY, reader.getAnnotation( Access.class )
				.value()
		);
	}
