	@Test
	public void testCollectionCacheEvictionUpdateWithEntityOutOfContext() {
		Session s = openSession();
		Company company1 = s.get( Company.class, 1 );
		Company company2 = s.get( Company.class, 2 );

		assertEquals( 1, company1.getUsers().size() );
		assertEquals( 0, company2.getUsers().size() );

		s.close();
		s = openSession();
		s.beginTransaction();

		User user = s.get( User.class, 1 );
		user.setCompany( company2 );

		s.getTransaction().commit();
		s.close();

		s = openSession();

		company1 = s.get( Company.class, 1 );
		company2 = s.get( Company.class, 2 );

		assertEquals( 1, company2.getUsers().size() );

		try {
			assertEquals( 0, company1.getUsers().size() );
		}
		catch ( ObjectNotFoundException e ) {
			fail( "Cached element not found" );
		}
		s.close();
	}
