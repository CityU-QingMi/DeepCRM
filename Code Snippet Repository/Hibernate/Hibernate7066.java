	@Test
	public void testCascadeSomeWithDefaultPersist() throws Exception {
		reader = getReader( Entity2.class, "field1", "one-to-many.orm21.xml" );
		assertAnnotationPresent( OneToMany.class );
		OneToMany relAnno = reader.getAnnotation( OneToMany.class );
		assertEquals( 4, relAnno.cascade().length );
		assertEquals( CascadeType.REMOVE, relAnno.cascade()[0] );
		assertEquals( CascadeType.REFRESH, relAnno.cascade()[1] );
		assertEquals( CascadeType.DETACH, relAnno.cascade()[2] );
		assertEquals( CascadeType.PERSIST, relAnno.cascade()[3] );
	}
