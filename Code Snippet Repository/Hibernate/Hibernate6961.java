	@Test
	public void testTransientAndEmbeddedRelatedAnnotations() throws Exception {
		XMLContext context = buildContext( "org/hibernate/test/annotations/reflection/orm.xml" );

		Field field = Administration.class.getDeclaredField( "transientField" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Transient.class ) );
		assertNull( reader.getAnnotation( Basic.class ) );

		field = Match.class.getDeclaredField( "playerASSN" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Embedded.class ) );
	}
