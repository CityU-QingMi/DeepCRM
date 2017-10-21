	@Test
	public void testUpdateMultipleValues() {
		final String name = "Name";
		final String lastName = "LastName";
		final List<Integer> ids = new ArrayList<Integer>();
		final int quantity = 10;
		final List<DestinationEntity> destinations = new ArrayList<DestinationEntity>();
		for ( int i = 0; i < quantity; i++ ) {
			FromEntity fe = createFrom( name + i, lastName + i );
			DestinationEntity destination = createDestination( fe, fe.name + fe.lastName );
			destinations.add( destination );
			ids.add( destination.id );
		}

		final String inverseFullName = lastName + " " + name;
		final FromEntity anotherFrom = createFrom( lastName, name );

		Session session = openSession();
		session.getTransaction().begin();
		Query update = session.getNamedQuery( "DestinationEntity.update" );
		update.setParameter( "idFrom", anotherFrom.id );
		update.setParameter( "fullName", inverseFullName );
		update.setParameterList( "ids", ids );

		int executeUpdate = update.executeUpdate();
		assertEquals( quantity, executeUpdate );

		session.getTransaction().commit();
		session.close();

		List<DestinationEntity> list = findDestinationByIds( ids );
		assertEquals( quantity, list.size() );

		for ( int i = 0; i < quantity; i++ ) {
			DestinationEntity updated = (DestinationEntity) list.get( i );

			assertEquals( anotherFrom, updated.from );
			assertEquals( inverseFullName, updated.fullNameFrom );
		}
	}
