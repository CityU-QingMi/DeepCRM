	@Test
	public void testCascadeSomeWithDefaultPersist() throws Exception {
		reader = getReader( Entity2.class, "field1", "many-to-many.orm19.xml" );
		assertAnnotationPresent( ManyToMany.class );
		ManyToMany relAnno = reader.getAnnotation( ManyToMany.class );
		assertEquals( 4, relAnno.cascade().length );
		assertEquals( CascadeType.REMOVE, relAnno.cascade()[0] );
		assertEquals( CascadeType.REFRESH, relAnno.cascade()[1] );
		assertEquals( CascadeType.DETACH, relAnno.cascade()[2] );
		assertEquals( CascadeType.PERSIST, relAnno.cascade()[3] );
	}
