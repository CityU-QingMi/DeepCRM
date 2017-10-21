	@Test
	public void testCascadeSomeWithDefaultPersist() throws Exception {
		reader = getReader( Entity1.class, "field1", "many-to-one.orm8.xml" );
		assertAnnotationPresent( ManyToOne.class );
		ManyToOne relAnno = reader.getAnnotation( ManyToOne.class );
		assertEquals( 4, relAnno.cascade().length );
		assertEquals( CascadeType.REMOVE, relAnno.cascade()[0] );
		assertEquals( CascadeType.REFRESH, relAnno.cascade()[1] );
		assertEquals( CascadeType.DETACH, relAnno.cascade()[2] );
		assertEquals( CascadeType.PERSIST, relAnno.cascade()[3] );
	}
