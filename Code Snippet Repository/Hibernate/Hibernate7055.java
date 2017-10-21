	@Test
	public void testCascadeAllPlusMore() throws Exception {
		reader = getReader( Entity1.class, "field1", "many-to-one.orm9.xml" );
		assertAnnotationPresent( ManyToOne.class );
		ManyToOne relAnno = reader.getAnnotation( ManyToOne.class );
		assertEquals( 6, relAnno.cascade().length );
		assertEquals( CascadeType.ALL, relAnno.cascade()[0] );
		assertEquals( CascadeType.PERSIST, relAnno.cascade()[1] );
		assertEquals( CascadeType.MERGE, relAnno.cascade()[2] );
		assertEquals( CascadeType.REMOVE, relAnno.cascade()[3] );
		assertEquals( CascadeType.REFRESH, relAnno.cascade()[4] );
		assertEquals( CascadeType.DETACH, relAnno.cascade()[5] );
	}
