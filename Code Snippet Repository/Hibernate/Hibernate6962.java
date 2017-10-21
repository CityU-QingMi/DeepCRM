	@Test
	public void testAssociationRelatedAnnotations() throws Exception {
		XMLContext context = buildContext( "org/hibernate/test/annotations/reflection/orm.xml" );

		Field field = Administration.class.getDeclaredField( "defaultBusTrip" );
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( OneToOne.class ) );
		assertNull( reader.getAnnotation( JoinColumns.class ) );
		assertNotNull( reader.getAnnotation( PrimaryKeyJoinColumns.class ) );
		assertEquals( "pk", reader.getAnnotation( PrimaryKeyJoinColumns.class ).value()[0].name() );
		assertEquals( 5, reader.getAnnotation( OneToOne.class ).cascade().length );
		assertEquals( FetchType.LAZY, reader.getAnnotation( OneToOne.class ).fetch() );
		assertEquals( "test", reader.getAnnotation( OneToOne.class ).mappedBy() );

		context = buildContext(
				"org/hibernate/test/annotations/reflection/metadata-complete.xml"
		);
		field = BusTrip.class.getDeclaredField( "players" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( OneToMany.class ) );
		assertNotNull( reader.getAnnotation( JoinColumns.class ) );
		assertEquals( 2, reader.getAnnotation( JoinColumns.class ).value().length );
		assertEquals( "driver", reader.getAnnotation( JoinColumns.class ).value()[0].name() );
		assertNotNull( reader.getAnnotation( MapKey.class ) );
		assertEquals( "name", reader.getAnnotation( MapKey.class ).name() );

		field = BusTrip.class.getDeclaredField( "roads" );
		reader = new JPAOverriddenAnnotationReader( field, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( ManyToMany.class ) );
		assertNotNull( reader.getAnnotation( JoinTable.class ) );
		assertEquals( "bus_road", reader.getAnnotation( JoinTable.class ).name() );
		assertEquals( 2, reader.getAnnotation( JoinTable.class ).joinColumns().length );
		assertEquals( 1, reader.getAnnotation( JoinTable.class ).inverseJoinColumns().length );
		assertEquals( 2, reader.getAnnotation( JoinTable.class ).uniqueConstraints()[0].columnNames().length );
		assertNotNull( reader.getAnnotation( OrderBy.class ) );
		assertEquals( "maxSpeed", reader.getAnnotation( OrderBy.class ).value() );
	}
