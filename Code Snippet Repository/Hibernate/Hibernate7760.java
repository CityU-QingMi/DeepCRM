	@Test
	@TestForIssue(jiraKey = "")
	public void testPostCommitDeleteListenerSuccess() {
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
		transaction.commit();

		session.close();

		Assert.assertEquals( 1, ((TestPostCommitDeleteEventListener) postCommitDeleteEventListener).success );
		Assert.assertEquals( 0, ((TestPostCommitDeleteEventListener) postCommitDeleteEventListener).failed );
	}
