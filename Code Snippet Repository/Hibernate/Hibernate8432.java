	@Test
	public void testInsertSingleValue() {
		final String name = "Name";
		final String lastName = "LastName";
		final String fullName = name + " " + lastName;
		final FromEntity fromEntity = createFrom( name, lastName );
		final int id = 10000;// id fake

		Session session = openSession();
		session.getTransaction().begin();
		Query insert = session.getNamedQuery( "DestinationEntity.insert" );
		insert.setParameter( "generatedId", id );
		insert.setParameter( "fromId", fromEntity.id );
		insert.setParameter( "fullName", fullName );
		int executeUpdate = insert.executeUpdate();
		assertEquals( 1, executeUpdate );
		session.getTransaction().commit();
		session.close();

		session = openSession();
		DestinationEntity get = (DestinationEntity) session.get( DestinationEntity.class, id );
		session.close();

		assertEquals( fromEntity, get.from );
		assertEquals( fullName, get.fullNameFrom );
	}
