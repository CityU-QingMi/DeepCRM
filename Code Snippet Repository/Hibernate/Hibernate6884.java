	@Test
	public void testEagerFetching() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Client c = new Client();
		c.setName( "Emmanuel" );
		Address a = new Address();
		a.setCity( "Courbevoie" );
		c.setAddress( a );
		s.persist( c );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Query q = s.createQuery( "select c from Client c where c.name = :name" );
		q.setString( "name", c.getName() );
		c = ( Client ) q.uniqueResult();
		//c = (Client) s.get(Client.class, c.getId());
		assertNotNull( c );
		tx.commit();
		s.close();
		assertNotNull( c.getAddress() );
		//assertTrue( "Should be eager fetched", Hibernate.isInitialized( c.getAddress() ) );

	}
