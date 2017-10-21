	@Test
	public void testMultipleMapKeyJoinColumns() throws Exception {
		reader = getReader( Entity3.class, "field1", "one-to-many.orm15.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationNotPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		MapKeyJoinColumns joinColumnsAnno = reader
				.getAnnotation( MapKeyJoinColumns.class );
		MapKeyJoinColumn[] joinColumns = joinColumnsAnno.value();
		assertEquals( 2, joinColumns.length );
		assertEquals( "", joinColumns[0].name() );
		assertEquals( "", joinColumns[0].referencedColumnName() );
		assertFalse( joinColumns[0].unique() );
		assertFalse( joinColumns[0].nullable() );
		assertTrue( joinColumns[0].insertable() );
		assertTrue( joinColumns[0].updatable() );
		assertEquals( "", joinColumns[0].columnDefinition() );
		assertEquals( "", joinColumns[0].table() );
		assertEquals( "col1", joinColumns[1].name() );
		assertEquals( "col2", joinColumns[1].referencedColumnName() );
		assertTrue( joinColumns[1].unique() );
		assertTrue( joinColumns[1].nullable() );
		assertFalse( joinColumns[1].insertable() );
		assertFalse( joinColumns[1].updatable() );
		assertEquals( "int", joinColumns[1].columnDefinition() );
		assertEquals( "table1", joinColumns[1].table() );
	}
