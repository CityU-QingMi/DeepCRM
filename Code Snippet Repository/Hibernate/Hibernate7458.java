	@Test
	public void testSimpleConvertUsage() throws MalformedURLException {
		final EntityPersister ep = sessionFactory().getEntityPersister( Entity1.class.getName() );
		final Type theDatePropertyType = ep.getPropertyType( "mediaType" );
		final AttributeConverterTypeAdapter type = assertTyping(
				AttributeConverterTypeAdapter.class,
				theDatePropertyType
		);
		assertTyping( MediaTypeConverter.class, type.getAttributeConverter() );

		resetFlags();

		Session session = openSession();
		session.getTransaction().begin();
		session.persist( new Entity1( 1, "300", MediaType.VIDEO ) );
		session.getTransaction().commit();
		session.close();

		assertTrue( convertToDatabaseColumnCalled );
		resetFlags();

		session = openSession();
		session.getTransaction().begin();
		session.get( Entity1.class, 1 );
		session.getTransaction().commit();
		session.close();

		assertTrue( convertToEntityAttributeCalled );

		session = openSession();
		session.getTransaction().begin();
		session.createQuery( "delete Entity1" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
