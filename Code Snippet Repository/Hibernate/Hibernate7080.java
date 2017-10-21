	@Test
	public void testSingleJoinColumn() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm4.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumns.class );
		assertAnnotationPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		assertAnnotationNotPresent( JoinTable.class );
		JoinColumns joinColumnsAnno = reader.getAnnotation( JoinColumns.class );
		JoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 1, joinColumns.length );
		assertEquals( "col1", joinColumns[0].name() );
		assertEquals( "col2", joinColumns[0].referencedColumnName() );
		assertEquals( "table1", joinColumns[0].table() );
	}
