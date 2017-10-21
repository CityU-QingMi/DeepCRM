	@Test
	public void testCollectionCacheEvictionInsert() {
		Session s = openSession();
		s.beginTransaction();

		Company company = (Company) s.get( Company.class, 1 );
		// init cache of collection
		assertEquals( 1, company.getUsers().size() );

		User user = new User( 2, company );
		s.save( user );

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();

		company = (Company) s.get( Company.class, 1 );
		// fails if cache is not evicted
		assertEquals( 2, company.getUsers().size() );

		s.close();
	}
