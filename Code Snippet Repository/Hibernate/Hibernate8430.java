	@Test
	public void testSingleSelect() {
		final String name = "Name";
		final String lastName = "LastName";
		final String fullName = name + " " + lastName;
		final DestinationEntity destination = createDestination( createFrom( name, lastName ), fullName );

		Session session = openSession();
		Query select = session.getNamedQuery( "DestinationEntity.selectIds" );
		select.setParameterList( "ids", Collections.singletonList( destination.id ) );
		Object[] unique = (Object[]) select.uniqueResult();
		session.close();

		// Compare the Strings, not the actual IDs.  Can come back as, for ex,
		// a BigDecimal in Oracle.
		assertEquals( destination.id + "", unique[0] + "" );
		assertEquals( destination.from.id + "", unique[1] + "" );
		assertEquals( destination.fullNameFrom, unique[2] );
	}
