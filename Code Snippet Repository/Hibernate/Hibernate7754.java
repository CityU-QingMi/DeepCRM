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

		//the legacy implementation fires the listener on failure as well
		Assert.assertEquals( 1, ((LegacyPostCommitDeleteEventListener) postCommitDeleteEventListener).fired );
	}
