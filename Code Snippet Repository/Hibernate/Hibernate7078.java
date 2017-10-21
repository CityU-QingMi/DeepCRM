	@Test
	public void testSinglePrimaryKeyJoinColumn() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm2.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationPresent( PrimaryKeyJoinColumns.class );
		PrimaryKeyJoinColumns joinColumnsAnno =
				reader.getAnnotation( PrimaryKeyJoinColumns.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		PrimaryKeyJoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 1, joinColumns.length );
		assertEquals( "col1", joinColumns[0].name() );
		assertEquals( "col2", joinColumns[0].referencedColumnName() );
		assertEquals( "int", joinColumns[0].columnDefinition() );
	}
