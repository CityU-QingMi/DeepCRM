	@Test
	public void testNullQueryResult() {
		Session s = openSession();
		s.beginTransaction();

		assertNull( s.createQuery( "select u from User u where u.id = -1" ).uniqueResult() );

		Optional<User> user = s.createQuery( "select u from User u where u.id = -1" ).uniqueResultOptional();
		assertNotNull( user );
		assertFalse( user.isPresent() );
		try {
			user.get();
			fail( "Expecting call to Optional#get to throw NoSuchElementException" );
		}
		catch (NoSuchElementException expected) {
			// the expected result...
		}

		s.getTransaction().commit();
		s.close();

	}
