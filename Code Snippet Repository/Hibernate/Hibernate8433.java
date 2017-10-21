	public void testInsertMultipleValues() {
		final String name = "Name";
		final String lastName = "LastName";
		final List<Integer> ids = new ArrayList<Integer>();
		final int quantity = 10;
		final List<FromEntity> froms = new ArrayList<FromEntity>();
		for ( int i = 0; i < quantity; i++ ) {
			FromEntity fe = createFrom( name + i, lastName + i );
			froms.add( fe );
			ids.add( fe.id );
		}

		Session session = openSession();
		session.getTransaction().begin();
		Query insertSelect = session.getNamedQuery( "DestinationEntity.insertSelect" );
		insertSelect.setParameterList( "ids", ids );
		int executeUpdate = insertSelect.executeUpdate();
		assertEquals( quantity, executeUpdate );

		session.getTransaction().commit();
		session.close();

		List<DestinationEntity> list = findDestinationByIds( ids );
		assertEquals( quantity, list.size() );

		for ( int i = 0; i < quantity; i++ ) {
			DestinationEntity de = (DestinationEntity) list.get( i );
			FromEntity from = froms.get( i );
			assertEquals( from, de.from );
			assertEquals( from.name + from.lastName, de.fullNameFrom );
		}
	}
