	@Test
	@TestForIssue(jiraKey = "")
	public void testPostCommitDeleteListenerRollback() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		IrrelevantEntity irrelevantEntity = new IrrelevantEntity();
		irrelevantEntity.setName( "Irrelevant" );

		session.save( irrelevantEntity );
		session.flush();
		transaction.commit();
		session.close();

		session = openSession();
		transaction = session.beginTransaction();
		session.delete( irrelevantEntity );
		session.flush();
		transaction.rollback();

		session.close();

		Assert.assertEquals( 0, ((TestPostCommitDeleteEventListener) postCommitDeleteEventListener).success );
		Assert.assertEquals( 1, ((TestPostCommitDeleteEventListener) postCommitDeleteEventListener).failed );
	}
