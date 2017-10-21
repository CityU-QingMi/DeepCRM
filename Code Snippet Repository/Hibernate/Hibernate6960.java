	@Test
	public void testVersionRelatedAnnotations() throws Exception {
		XMLContext context = buildContext( "org/hibernate/test/annotations/reflection/orm.xml" );
		Method method = Administration.class.getDeclaredMethod( "getVersion" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Version.class ) );

		Field field = Match.class.getDeclaredField( "version" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Version.class ) );
	}
