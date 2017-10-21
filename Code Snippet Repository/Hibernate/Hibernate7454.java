	@Test
	public void testSimpleConvertUsage() throws MalformedURLException {
		final EntityPersister ep = sessionFactory().getEntityPersister( TheEntity.class.getName() );
		final Type theDatePropertyType = ep.getPropertyType( "theDate" );
		final AttributeConverterTypeAdapter type = assertTyping( AttributeConverterTypeAdapter.class, theDatePropertyType );
		assertTyping( JodaLocalDateConverter.class, type.getAttributeConverter() );

		resetFlags();

		Session session = openSession();
		session.getTransaction().begin();
		session.persist( new TheEntity( 1, new LocalDate() ) );
		session.getTransaction().commit();
		session.close();

		assertTrue( convertToDatabaseColumnCalled );
		resetFlags();

		session = openSession();
		session.getTransaction().begin();
		session.get( TheEntity.class, 1 );
		session.getTransaction().commit();
		session.close();

		assertTrue( convertToEntityAttributeCalled );
		resetFlags();

		session = openSession();
		session.getTransaction().begin();
		session.createQuery( "delete TheEntity" ).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
