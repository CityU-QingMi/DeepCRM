	@Test
	public void testNullLoadResult() {
		Session s = openSession();
		s.beginTransaction();

		assertNull( s.byId( User.class ).load( -1 ) );

		Optional<User> user = s.byId( User.class ).loadOptional( -1 );
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
