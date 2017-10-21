	@Test
	@TestForIssue( jiraKey = "" )
	public void testExpectations() {
		// JPA spec is very vague on what should happen here.  It does vaguely
		// imply that javax.persistence.EntityManager.joinTransaction() should only be used
		// for JTA EMs, however it does not enforced that nor does the TCK check that.
		// And the TCK in fact does test calls to javax.persistence.EntityManager.isJoinedToTransaction()
		// from resource-local EMs, so lets make sure those work..

		Session session = sessionFactory().openSession();
		JdbcResourceLocalTransactionCoordinatorImpl tc = ExtraAssertions.assertTyping(
				JdbcResourceLocalTransactionCoordinatorImpl.class,
				( (SessionImplementor) session ).getTransactionCoordinator()
		);
		assertFalse( tc.isJoined() );

		session.beginTransaction();
		tc = ExtraAssertions.assertTyping(
				JdbcResourceLocalTransactionCoordinatorImpl.class,
				( (SessionImplementor) session ).getTransactionCoordinator()
		);
		assertTrue( tc.isJoined() );

		session.getTransaction().rollback();
		session.close();
	}
