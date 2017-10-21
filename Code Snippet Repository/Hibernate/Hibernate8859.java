	@Test
	public void testNaturalIdCheck() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		User u = new User( "steve", "superSecret" );
		s.persist( u );
		u.setUserName( "Steve" );
		try {
			s.flush();
			fail();
		}
		catch ( PersistenceException e ) {
			//expected
			t.rollback();
		}
		u.setUserName( "steve" );
		s.delete( u );
		s.close();
	}
