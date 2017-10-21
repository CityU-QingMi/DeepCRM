	@Test
	public void testCascadeAllPlusMore() throws Exception {
		reader = getReader( Entity2.class, "field1", "one-to-many.orm22.xml" );
		assertAnnotationPresent( OneToMany.class );
		OneToMany relAnno = reader.getAnnotation( OneToMany.class );
		assertEquals( 6, relAnno.cascade().length );
		assertEquals( CascadeType.ALL, relAnno.cascade()[0] );
		assertEquals( CascadeType.PERSIST, relAnno.cascade()[1] );
		assertEquals( CascadeType.MERGE, relAnno.cascade()[2] );
		assertEquals( CascadeType.REMOVE, relAnno.cascade()[3] );
		assertEquals( CascadeType.REFRESH, relAnno.cascade()[4] );
		assertEquals( CascadeType.DETACH, relAnno.cascade()[5] );
	}
