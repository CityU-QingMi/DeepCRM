	@Test
	public void testBasicRelatedAnnotations() throws Exception {
		XMLContext context = buildContext(
				"org/hibernate/test/annotations/reflection/metadata-complete.xml"
		);
		Field field = BusTrip.class.getDeclaredField( "status" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Enumerated.class ) );
		assertEquals( EnumType.STRING, reader.getAnnotation( Enumerated.class ).value() );
		assertEquals( false, reader.getAnnotation( Basic.class ).optional() );
		field = BusTrip.class.getDeclaredField( "serial" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Lob.class ) );
		assertEquals( "serialbytes", reader.getAnnotation( Columns.class ).columns()[0].name() );
		field = BusTrip.class.getDeclaredField( "terminusTime" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Temporal.class ) );
		assertEquals( TemporalType.TIMESTAMP, reader.getAnnotation( Temporal.class ).value() );
		assertEquals( FetchType.LAZY, reader.getAnnotation( Basic.class ).fetch() );

		field = BusTripPk.class.getDeclaredField( "busDriver" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.isAnnotationPresent( Basic.class ) );
	}
