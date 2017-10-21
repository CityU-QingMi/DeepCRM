	@Test
	@TestForIssue(jiraKey = "")
	public void testPostCommitInsertListenerSuccess() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();

		IrrelevantEntity irrelevantEntity = new IrrelevantEntity();
		irrelevantEntity.setName( "Irrelevant" );

		session.save( irrelevantEntity );
		session.flush();
		transaction.commit();
		session.close();

		Assert.assertEquals( 1, ((LegacyPostCommitInsertEventListener) postCommitInsertEventListener).fired );
	}
