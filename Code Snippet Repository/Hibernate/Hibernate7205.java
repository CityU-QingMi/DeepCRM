	@Test
	@TestForIssue(jiraKey = "")
	public void testCollectionCacheEvictionUpdateWhenChildIsSetToNull() {
		Session s = openSession();
		s.beginTransaction();

		Company company1 = (Company) s.get( Company.class, 1 );
		Company company2 = (Company) s.get( Company.class, 2 );

		// init cache of collection
		assertEquals( 1, company1.getUsers().size() );
		assertEquals( 0, company2.getUsers().size() );

		User user = (User) s.get( User.class, 1 );
		user.setCompany( null );

		s.getTransaction().commit();
		s.close();

		s = openSession();

		company1 = (Company) s.get( Company.class, 1 );
		company2 = (Company) s.get( Company.class, 2 );

		assertEquals( 0, company1.getUsers().size() );
		assertEquals( 0, company2.getUsers().size() );
		s.close();
	}
