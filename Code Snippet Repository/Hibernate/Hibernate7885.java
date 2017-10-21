	@Test
	public void testOrderByEntityWithFetchJoinedCollection() {
		createData();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		// ordered by address desc, name desc:
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		// using DESC
		List list = s.createQuery( "from Zoo z join fetch z.mammals" ).list();

		t.commit();
		s.close();

		cleanupData();
	}
