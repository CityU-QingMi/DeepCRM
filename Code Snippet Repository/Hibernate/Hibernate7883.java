	@Test
	@FailureExpected( jiraKey = "" )
	public void testOrderByComponentDescNoSelectAliasRef() {
		createData();

		Session s = openSession();
		Transaction t = s.beginTransaction();

		// ordered by address DESC, name DESC:
		//   zoo3  Zoo         1312 Mockingbird Lane, Anywhere, IL USA
		//   zoo4  Duh Zoo     1312 Mockingbird Lane, Nowhere, IL USA
		//   zoo2  A Zoo       1313 Mockingbird Lane, Anywhere, IL USA
		//   zoo1  Zoo         1313 Mockingbird Lane, Anywhere, IL USA
		checkTestOrderByResults(
				s.createQuery(
						"select z.name, z.address from Zoo z order by z.address DESC, z.name DESC"
				).list(),
				zoo1, zoo2, zoo4, zoo3, null
		);
		checkTestOrderByResults(
				s.createQuery(
						"select name, address from Zoo order by address DESC, name DESC"
				).list(),
				zoo1, zoo2, zoo4, zoo3, null
		);
		t.commit();
		s.close();
		cleanupData();
	}
