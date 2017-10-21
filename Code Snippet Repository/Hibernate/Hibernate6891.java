	@Test
	public void testOneToOneWithExplicitSecondaryTableFk() throws Exception {
		Client c = new Client();
		Address a = new Address();
		a.setCity( "Paris" );
		c.setName( "Emmanuel" );
		c.setSecondaryAddress( a );

		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		c = ( Client ) s.get( Client.class, c.getId() );
		assertNotNull( c );
		assertNotNull( c.getSecondaryAddress() );
		assertEquals( "Paris", c.getSecondaryAddress().getCity() );
		tx.commit();
		s.close();
	}
