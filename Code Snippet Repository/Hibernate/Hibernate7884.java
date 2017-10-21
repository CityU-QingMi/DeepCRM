	@Test
	@FailureExpected( jiraKey = "")
	public void testOrderByComponentDescSelectAliasRefFailureExpected() {
		createData();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		// ordered by address desc, name desc:
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		// using DESC
		checkTestOrderByResults(
				s.createQuery(
						"select z.name as zooName, z.address as zooAddress from Zoo z order by zooAddress DESC, zooName DESC"
				).list(),
				zoo1, zoo2, zoo4, zoo3, null
		);

		t.commit();
		s.close();

		cleanupData();
	}
