	@Test
	public void testCollectionCacheEvictionRemoveWithEntityOutOfContext() {
		Session s = openSession();
		Company company = s.get( Company.class, 1 );
		assertEquals( 1, company.getUsers().size() );
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( company.getUsers().get( 0 ) );

		s.getTransaction().commit();
		s.close();

		s = openSession();

		company = s.get( Company.class, 1 );
		try {
			assertEquals( 0, company.getUsers().size() );
		}
		catch ( ObjectNotFoundException e ) {
			fail( "Cached element not found" );
		}
		s.close();
	}
