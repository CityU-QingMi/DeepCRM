	@Test
	@TestForIssue( jiraKey = "" )
	public void testSharedTransactionContextFlushBeforeCompletion() {
		Session session = sessionFactory().openSession();
		session.getTransaction().begin();

		Session secondSession = session.sessionWithOptions()
				.transactionContext()
				.flushBeforeCompletion( true )
				.autoClose( true )
				.openSession();

		// directly assert state of the second session
//		assertTrue( ((SessionImplementor) secondSession).isFlushBeforeCompletionEnabled() );

		// now try it out
		Integer id = (Integer) secondSession.save( new IrrelevantEntity() );
		session.getTransaction().commit();
		assertFalse( ((SessionImplementor) session).isClosed() );
		assertTrue( ((SessionImplementor) secondSession).isClosed() );

		session.close();
		assertTrue( ((SessionImplementor) session).isClosed() );
		assertTrue( ((SessionImplementor) secondSession).isClosed() );

		session = sessionFactory().openSession();
		session.getTransaction().begin();
		IrrelevantEntity it = (IrrelevantEntity) session.byId( IrrelevantEntity.class ).load( id );
		assertNotNull( it );
		session.delete( it );
		session.getTransaction().commit();
		session.close();
	}
