	@Test
	public void testEntityListeners() throws Exception {
		XMLContext context = buildContext( "org/hibernate/test/annotations/reflection/orm.xml" );

		Method method = Administration.class.getDeclaredMethod( "calculate" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertTrue( reader.isAnnotationPresent( PrePersist.class ) );

		reader = new JPAOverriddenAnnotationReader( Administration.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertTrue( reader.isAnnotationPresent( EntityListeners.class ) );
		assertEquals( 1, reader.getAnnotation( EntityListeners.class ).value().length );
		assertEquals( LogListener.class, reader.getAnnotation( EntityListeners.class ).value()[0] );

		method = LogListener.class.getDeclaredMethod( "noLog", Object.class );
		reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertTrue( reader.isAnnotationPresent( PostLoad.class ) );

		method = LogListener.class.getDeclaredMethod( "log", Object.class );
		reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertTrue( reader.isAnnotationPresent( PrePersist.class ) );
		assertFalse( reader.isAnnotationPresent( PostPersist.class ) );

		assertEquals( 1, context.getDefaultEntityListeners().size() );
		assertEquals( OtherLogListener.class.getName(), context.getDefaultEntityListeners().get( 0 ) );
	}
