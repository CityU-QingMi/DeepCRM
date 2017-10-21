	@TestForIssue(jiraKey = "")
	@Test
	public void testReadLockModeOnEntityLoad() {

		// first evict user
		sessionFactory().getCache().evictEntity( User.class.getName(), 1 );

		Session s1 = openSession();
		s1.beginTransaction();

		Company company1 = s1.get( Company.class, 1 );

/**/
/**/
/**/
		Session s2 = openSession();
		s2.beginTransaction();
		Company company = s2.get( Company.class, 1 );
		company.setName( "TestCompany" );
		s2.getTransaction().commit();
		s2.close();


/**/
/**/
/**/

		User user1 = s1.get( User.class, 1, LockMode.READ ); // into persistent context

		// init cache of collection
		assertNull( user1.getCompany().getName() ); // raises org.hibernate.StaleObjectStateException if 2LCache is enabled

		s1.getTransaction().commit();
		s1.close();
	}
