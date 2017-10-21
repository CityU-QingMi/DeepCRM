	@Test
	public void testDeleteMultipleValues() {
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

		Session session = openSession();
		session.getTransaction().begin();
		Query delete = session.getNamedQuery( "DestinationEntity.delete" );
		delete.setParameterList( "ids", ids );

		int executeUpdate = delete.executeUpdate();
		assertEquals( quantity, executeUpdate );

		session.getTransaction().commit();
		session.close();

		List<DestinationEntity> list = findDestinationByIds( ids );
		assertTrue( list.isEmpty() );
	}
