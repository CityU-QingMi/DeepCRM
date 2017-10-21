	@Test
	@TestForIssue(jiraKey = "")
	public void testPostCommitUpdateListenerRollback() {
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
		irrelevantEntity.setName( "Irrelevant 2" );
		session.update( irrelevantEntity );
		session.flush();
		transaction.rollback();

		session.close();

		Assert.assertEquals( 0, ((TestPostCommitUpdateEventListener) postCommitUpdateEventListener).sucess );
		Assert.assertEquals( 1, ((TestPostCommitUpdateEventListener) postCommitUpdateEventListener).failed );
	}
