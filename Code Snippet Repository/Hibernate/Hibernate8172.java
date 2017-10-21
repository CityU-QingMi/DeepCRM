	@Test
	@TestForIssue(jiraKey = "")
	public void testWithClauseAsSubquery() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		// Since friends has a join table, we will first left join all friends and then do the WITH clause on the target entity table join
		// Normally this produces 2 results which is wrong and can only be circumvented by converting the join table and target entity table join to a subquery
		List list = s.createQuery( "from Human h left join h.friends as f with f.nickName like 'bubba' where h.description = 'father'" )
				.list();
		assertEquals( "subquery rewriting of join table did not take effect", 1, list.size() );

		txn.commit();
		s.close();

		data.cleanup();
	}
