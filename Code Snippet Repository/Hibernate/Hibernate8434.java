	@Test
	public void testUpdateSingleValue() {
		final String name = "Name";
		final String lastName = "LastName";
		final String fullName = name + " " + lastName;

		final FromEntity fromEntity = createFrom( name, lastName );
		final DestinationEntity destinationEntity = createDestination( fromEntity, fullName );

		final String inverseFullName = lastName + " " + name;
		final FromEntity anotherFrom = createFrom( lastName, name );

		Session session = openSession();
		session.getTransaction().begin();
		Query update = session.getNamedQuery( "DestinationEntity.update" );
		update.setParameter( "idFrom", anotherFrom.id );
		update.setParameter( "fullName", inverseFullName );
		update.setParameterList( "ids", Collections.singletonList( destinationEntity.id ) );

		int executeUpdate = update.executeUpdate();
		assertEquals( 1, executeUpdate );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		session.beginTransaction();
		DestinationEntity get = (DestinationEntity) session.get( DestinationEntity.class, destinationEntity.id );

		assertEquals( anotherFrom, get.from );
		assertEquals( inverseFullName, get.fullNameFrom );
		session.getTransaction().commit();
		session.close();
	}
