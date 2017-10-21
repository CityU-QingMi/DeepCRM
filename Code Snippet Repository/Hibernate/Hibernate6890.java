	@Test
	public void testOneToOneWithExplicitFk() throws Exception {
		Client c = new Client();
		Address a = new Address();
		a.setCity( "Paris" );
		c.setName( "Emmanuel" );
		c.setAddress( a );

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
		assertNotNull( c.getAddress() );
		assertEquals( "Paris", c.getAddress().getCity() );
		tx.commit();
		s.close();
	}
