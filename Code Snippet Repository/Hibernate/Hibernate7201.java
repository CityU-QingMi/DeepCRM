	@Test
	public void testCollectionCacheEvictionInsertWithEntityOutOfContext() {
		Session s = openSession();
		Company company = s.get( Company.class, 1 );
		assertEquals( 1, company.getUsers().size() );
		s.close();

		s = openSession();
		s.beginTransaction();

		User user = new User( 2, company );
		s.save( user );

		s.getTransaction().commit();
		s.close();

		s = openSession();

		company = s.get( Company.class, 1 );
		assertEquals( 2, company.getUsers().size() );
		s.close();
	}
