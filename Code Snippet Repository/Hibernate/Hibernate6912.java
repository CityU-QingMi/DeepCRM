	@Test
	public void testOverriding() throws Exception {
		Location paris = new Location();
		paris.setName( "Paris" );
		Location atlanta = new Location();
		atlanta.setName( "Atlanta" );
		Trip trip = new Trip();
		trip.setFrom( paris );
		//trip.setTo( atlanta );
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		s.persist( paris );
		s.persist( atlanta );
		try {
			s.persist( trip );
			s.flush();
			fail( "Should be non nullable" );
		}
		catch (PersistenceException e) {
			//success
		}
		finally {
			tx.rollback();
			s.close();
		}
	}
