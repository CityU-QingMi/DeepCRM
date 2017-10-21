	@Test
	@TestForIssue(jiraKey = "")
	public void testWithClauseAsSubqueryWithKey() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		// Since family has a join table, we will first left join all family members and then do the WITH clause on the target entity table join
		// Normally this produces 2 results which is wrong and can only be circumvented by converting the join table and target entity table join to a subquery
		List list = s.createQuery( "from Human h left join h.family as f with key(f) like 'son1' where h.description = 'father'" )
				.list();
		assertEquals( "subquery rewriting of join table did not take effect", 1, list.size() );

		txn.commit();
		s.close();

		data.cleanup();
	}
