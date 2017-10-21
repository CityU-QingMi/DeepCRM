	@Test
	@TestForIssue(jiraKey = "")
	public void testWithClauseAsSubqueryWithKeyAndOtherJoinReference() {
		TestData data = new TestData();
		data.prepare();

		Session s = openSession();
		Transaction txn = s.beginTransaction();

		// Just a stupid example that makes use of a column that isn't from the collection table or the target entity table
		List list = s.createQuery( "from Human h join h.friends as friend left join h.family as f with key(f) = concat('son', cast(friend.intValue as string)) where h.description = 'father'" )
				.list();
		assertEquals( "subquery rewriting of join table did not take effect", 2, list.size() );

		txn.commit();
		s.close();

		data.cleanup();
	}
