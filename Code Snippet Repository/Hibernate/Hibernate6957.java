	@Test
	public void testEntityRelatedAnnotationsMetadataComplete() throws Exception {
		XMLContext context = buildContext(
				"org/hibernate/test/annotations/reflection/metadata-complete.xml"
		);
		JPAOverriddenAnnotationReader reader = new JPAOverriddenAnnotationReader( Administration.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Entity.class ) );
		assertEquals(
				"Metadata complete should ignore java annotations", "", reader.getAnnotation( Entity.class ).name()
		);
		assertNotNull( reader.getAnnotation( Table.class ) );
		assertEquals( "@Table should not be used", "", reader.getAnnotation( Table.class ).name() );
		assertEquals( "Default schema not overriden", "myschema", reader.getAnnotation( Table.class ).schema() );

		reader = new JPAOverriddenAnnotationReader( Match.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNotNull( reader.getAnnotation( Table.class ) );
		assertEquals( "@Table should not be used", "", reader.getAnnotation( Table.class ).name() );
		assertEquals( "Overriding not taken into account", "myschema", reader.getAnnotation( Table.class ).schema() );
		assertEquals( "Overriding not taken into account", "mycatalog", reader.getAnnotation( Table.class ).catalog() );
		assertNull( "Ignore Java annotation", reader.getAnnotation( SecondaryTable.class ) );
		assertNull( "Ignore Java annotation", reader.getAnnotation( SecondaryTables.class ) );
		assertNull( "Ignore Java annotation", reader.getAnnotation( Inheritance.class ) );
		assertNull( reader.getAnnotation( NamedQueries.class ) );
		assertNull( reader.getAnnotation( NamedNativeQueries.class ) );

		reader = new JPAOverriddenAnnotationReader( TennisMatch.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull( reader.getAnnotation( PrimaryKeyJoinColumn.class ) );
		assertNull( reader.getAnnotation( PrimaryKeyJoinColumns.class ) );

		reader = new JPAOverriddenAnnotationReader( Competition.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull( reader.getAnnotation( MappedSuperclass.class ) );

		reader = new JPAOverriddenAnnotationReader( SocialSecurityMoralAccount.class, context, ClassLoaderAccessTestingImpl.INSTANCE );
		assertNull( reader.getAnnotation( IdClass.class ) );
		assertNull( reader.getAnnotation( DiscriminatorValue.class ) );
		assertNull( reader.getAnnotation( DiscriminatorColumn.class ) );
		assertNull( reader.getAnnotation( SequenceGenerator.class ) );
		assertNull( reader.getAnnotation( TableGenerator.class ) );
	}
