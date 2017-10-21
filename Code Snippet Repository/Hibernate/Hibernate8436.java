	@Test
	public void testDeleteSingleValue() {
		final String name = "Name";
		final String lastName = "LastName";
		final String fullName = name + " " + lastName;

		final FromEntity fromEntity = createFrom( name, lastName );
		final DestinationEntity destinationEntity = createDestination( fromEntity, fullName );

		Session session = openSession();
		session.getTransaction().begin();
		Query delete = session.getNamedQuery( "DestinationEntity.delete" );
		delete.setParameterList( "ids", Collections.singletonList( destinationEntity.id ) );

		int executeUpdate = delete.executeUpdate();
		assertEquals( 1, executeUpdate );

		session.getTransaction().commit();
		session.close();

		session = openSession();
		DestinationEntity get = (DestinationEntity) session.get( DestinationEntity.class, destinationEntity.id );
		session.close();

		assertNull( get );
	}
