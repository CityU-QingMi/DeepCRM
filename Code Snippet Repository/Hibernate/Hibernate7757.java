	@Test
	@TestForIssue(jiraKey = "")
	public void testPostCommitInsertListenerRollback() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		IrrelevantEntity irrelevantEntity = new IrrelevantEntity();
		irrelevantEntity.setName( "Irrelevant" );

		session.save( irrelevantEntity );
		session.flush();
		transaction.rollback();
		session.close();

		Assert.assertEquals( 0, ((TestPostCommitInsertEventListener) postCommitInsertEventListener).success );
		Assert.assertEquals( 1, ((TestPostCommitInsertEventListener) postCommitInsertEventListener).failed );
	}
