	@Test
	@TestForIssue( jiraKey = "" )
	public void testChildSessionTwoTransactions() throws Exception {
		Session session = openSession();
		
		session.getTransaction().begin();
		
		//open secondary session with managed options
		Session secondarySession = session.sessionWithOptions()
				.connection()
				.flushBeforeCompletion( true )
				.autoClose( true )
				.openSession();
		
		//the secondary session should be automatically closed after the commit
		session.getTransaction().commit();
		
		assertFalse( secondarySession.isOpen() );

		//should be able to create a new transaction and carry on using the original session
		session.getTransaction().begin();
		session.getTransaction().commit();
	}
