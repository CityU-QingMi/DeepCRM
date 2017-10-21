	@Test
	@TestForIssue(jiraKey = "")
	public void testWithClauseAsSubqueryWithEqualOperator() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		// Like testWithClauseAsSubquery but uses equal operator since it render differently in SQL
		List list = s.createQuery( "from Human h left join h.friends as f with f.nickName = 'bubba' where h.description = 'father'" )
				.list();
		assertEquals( "subquery rewriting of join table did not take effect", 1, list.size() );

		txn.commit();
		s.close();

		data.cleanup();
	}
