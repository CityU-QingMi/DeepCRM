	@Test
	public void testMultipleSelect() {
		final String name = "Name";
		final String lastName = "LastName";
		final List<Integer> ids = new ArrayList<Integer>();
		final int quantity = 10;
		final List<DestinationEntity> destinations = new ArrayList<DestinationEntity>();
		for ( int i = 0; i < quantity; i++ ) {
			DestinationEntity createDestination = createDestination( createFrom( name + i, lastName + i ), name + i
					+ lastName + i );
			ids.add( createDestination.id );
			destinations.add( createDestination );
		}

		Session session = openSession();
		Query select = session.getNamedQuery( "DestinationEntity.selectIds" );
		select.setParameterList( "ids", ids );
		List list = select.list();
		session.close();

		assertEquals( quantity, list.size() );
		for ( int i = 0; i < list.size(); i++ ) {
			Object[] object = (Object[]) list.get( i );
			DestinationEntity destination = destinations.get( i );
			// Compare the Strings, not the actual IDs.  Can come back as, for ex,
			// a BigDecimal in Oracle.
			assertEquals( destination.id + "", object[0] + "" );
			assertEquals( destination.from.id + "", object[1] + "" );
			assertEquals( destination.fullNameFrom, object[2] );
		}
	}
