	@Test
	public void testJoinTableNoChildren() throws Exception {
		reader = getReader( Entity1.class, "field1", "one-to-one.orm6.xml" );
		assertAnnotationPresent( OneToOne.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumn.class );
		assertAnnotationNotPresent( PrimaryKeyJoinColumns.class );
		assertAnnotationPresent( JoinTable.class );
		assertAnnotationNotPresent( JoinColumns.class );
		assertAnnotationNotPresent( JoinColumn.class );
		JoinTable joinTableAnno = reader.getAnnotation( JoinTable.class );
		assertEquals( "", joinTableAnno.catalog() );
		assertEquals( "", joinTableAnno.name() );
		assertEquals( "", joinTableAnno.schema() );
		assertEquals( 0, joinTableAnno.joinColumns().length );
		assertEquals( 0, joinTableAnno.inverseJoinColumns().length );
		assertEquals( 0, joinTableAnno.uniqueConstraints().length );
	}
