	@Test
	public void testIdRelatedAnnotations() throws Exception {
		XMLContext context = buildContext( "org/hibernate/test/annotations/reflection/orm.xml" );
		Method method = Administration.class.getDeclaredMethod( "getId" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull( reader.getAnnotation( Id.class ) );
		assertNull( reader.getAnnotation( Column.class ) );
		Field field = Administration.class.getDeclaredField( "id" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Id.class ) );
		assertNotNull( reader.getAnnotation( GeneratedValue.class ) );
		assertEquals( GenerationType.SEQUENCE, reader.getAnnotation( GeneratedValue.class ).strategy() );
		assertEquals( "generator", reader.getAnnotation( GeneratedValue.class ).generator() );
		assertNotNull( reader.getAnnotation( SequenceGenerator.class ) );
		assertEquals( "seq", reader.getAnnotation( SequenceGenerator.class ).sequenceName() );
		assertNotNull( reader.getAnnotation( Columns.class ) );
		assertEquals( 1, reader.getAnnotation( Columns.class ).columns().length );
		assertEquals( "fld_id", reader.getAnnotation( Columns.class ).columns()[0].name() );
		assertNotNull( reader.getAnnotation( Temporal.class ) );
		assertEquals( TemporalType.DATE, reader.getAnnotation( Temporal.class ).value() );

		context = buildContext(
				"org/hibernate/test/annotations/reflection/metadata-complete.xml"
		);
		method = Administration.class.getDeclaredMethod( "getId" );
		reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull(
				"Default access type when not defined in metadata complete should be property",
				reader.getAnnotation( Id.class )
		);
		field = Administration.class.getDeclaredField( "id" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull(
				"Default access type when not defined in metadata complete should be property",
				reader.getAnnotation( Id.class )
		);

		method = BusTrip.class.getDeclaredMethod( "getId" );
		reader = new JPAOverriddenAnnotationReader( method, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull( reader.getAnnotation( EmbeddedId.class ) );
		field = BusTrip.class.getDeclaredField( "id" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( EmbeddedId.class ) );
		assertNotNull( reader.getAnnotation( AttributeOverrides.class ) );
		assertEquals( 1, reader.getAnnotation( AttributeOverrides.class ).value().length );
	}
