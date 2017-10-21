	@Test
	public void testCollectionCacheEvictionRemove() {
		Session s = openSession();
		s.beginTransaction();

		Company company = (Company) s.get( Company.class, 1 );
		// init cache of collection
		assertEquals( 1, company.getUsers().size() );

		s.delete( company.getUsers().get( 0 ) );

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		company = (Company) s.get( Company.class, 1 );
		// fails if cache is not evicted
		try {
			assertEquals( 0, company.getUsers().size() );
		}
		catch ( ObjectNotFoundException e ) {
			fail( "Cached element not found" );
		}
		s.close();
	}
