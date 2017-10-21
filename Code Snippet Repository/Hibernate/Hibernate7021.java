	@Test
	public void testCollectionTableAllChildren() throws Exception {
		reader = getReader( Entity3.class, "field1", "element-collection.orm27.xml" );
		assertAnnotationPresent( ElementCollection.class );
		assertAnnotationPresent( CollectionTable.class );
		CollectionTable tableAnno = reader.getAnnotation( CollectionTable.class );
		assertEquals( "table1", tableAnno.name() );
		assertEquals( "catalog1", tableAnno.catalog() );
		assertEquals( "schema1", tableAnno.schema() );

		//JoinColumns
		JoinColumn[] joinColumns = tableAnno.joinColumns();
		assertEquals( 2, joinColumns.length );
		assertEquals( "", joinColumns[0].name() );
		assertEquals( "", joinColumns[0].referencedColumnName() );
		assertEquals( "", joinColumns[0].table() );
		assertEquals( "", joinColumns[0].columnDefinition() );
		assertTrue( joinColumns[0].insertable() );
		assertTrue( joinColumns[0].updatable() );
		assertTrue( joinColumns[0].nullable() );
		assertFalse( joinColumns[0].unique() );
		assertEquals( "col1", joinColumns[1].name() );
		assertEquals( "col2", joinColumns[1].referencedColumnName() );
		assertEquals( "table2", joinColumns[1].table() );
		assertEquals( "int", joinColumns[1].columnDefinition() );
		assertFalse( joinColumns[1].insertable() );
		assertFalse( joinColumns[1].updatable() );
		assertFalse( joinColumns[1].nullable() );
		assertTrue( joinColumns[1].unique() );

		//UniqueConstraints
		UniqueConstraint[] uniqueConstraints = tableAnno.uniqueConstraints();
		assertEquals( 2, uniqueConstraints.length );
		assertEquals( "", uniqueConstraints[0].name() );
		assertEquals( 1, uniqueConstraints[0].columnNames().length );
		assertEquals( "col3", uniqueConstraints[0].columnNames()[0] );
		assertEquals( "uq1", uniqueConstraints[1].name() );
		assertEquals( 2, uniqueConstraints[1].columnNames().length );
		assertEquals( "col4", uniqueConstraints[1].columnNames()[0] );
		assertEquals( "col5", uniqueConstraints[1].columnNames()[1] );
	}
