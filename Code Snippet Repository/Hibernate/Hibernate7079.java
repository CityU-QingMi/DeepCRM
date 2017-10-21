	@Test
	public void testMultiplePrimaryKeyJoinColumn() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm3.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationPresent( PrimaryKeyJoinColumns.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		PrimaryKeyJoinColumns joinColumnsAnno =
				reader.getAnnotation( PrimaryKeyJoinColumns.class );
		PrimaryKeyJoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 2, joinColumns.length );
		assertEquals( "", joinColumns[0].name() );
		assertEquals( "", joinColumns[0].referencedColumnName() );
		assertEquals( "", joinColumns[0].columnDefinition() );
		assertEquals( "col1", joinColumns[1].name() );
		assertEquals( "col2", joinColumns[1].referencedColumnName() );
		assertEquals( "int", joinColumns[1].columnDefinition() );
	}
