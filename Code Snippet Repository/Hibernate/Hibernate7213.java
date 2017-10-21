	@TestForIssue(jiraKey = "")
	@Test
	public void testDefaultLockModeOnCollectionInitialization() {
		Session s1 = openSession();
		s1.beginTransaction();

		Company company1 = s1.get( Company.class, 1 );

		User user1 = s1.get( User.class, 1 ); // into persistent context

/**/
/**/
/**/
		Session s2 = openSession();
		s2.beginTransaction();
		User user = s2.get( User.class, 1 );
		user.setName("TestUser");
		s2.getTransaction().commit();
		s2.close();


/**/
/**/
/**/

		// init cache of collection
		assertEquals( 1, company1.getUsers().size() ); // raises org.hibernate.StaleObjectStateException if 2LCache is enabled


		s1.getTransaction().commit();
		s1.close();
	}
