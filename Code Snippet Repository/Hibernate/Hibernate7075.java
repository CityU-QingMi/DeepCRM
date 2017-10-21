	@Test
	public void testMapKeyTemporal() throws Exception {
		reader = getReader( Entity3.class, "field1", "one-to-many.orm8.xml" );
		assertAnnotationPresent( OneToMany.class );
		assertAnnotationNotPresent( MapKey.class );
		assertAnnotationNotPresent( MapKeyClass.class );
		assertAnnotationPresent( MapKeyTemporal.class );
		assertAnnotationNotPresent( MapKeyEnumerated.class );
		assertAnnotationNotPresent( MapKeyColumn.class );
		assertAnnotationNotPresent( MapKeyJoinColumns.class );
		assertAnnotationNotPresent( MapKeyJoinColumn.class );
		assertEquals(
				TemporalType.DATE, reader.getAnnotation(
				MapKeyTemporal.class
		).value()
		);
	}
