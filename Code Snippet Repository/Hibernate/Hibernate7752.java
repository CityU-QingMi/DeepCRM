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

		//the legacy implementation fires the listener on failure as well
		Assert.assertEquals( 1, ((LegacyPostCommitUpdateEventListener) postCommitUpdateEventListener).fired );
	}
