	@Test
	@TestForIssue( jiraKey = "" )
	public void testChildSessionCallsAfterTransactionAction() throws Exception {
		Session session = openSession();

		final String postCommitMessage = "post commit was called";
		
		EventListenerRegistry eventListenerRegistry = sessionFactory().getServiceRegistry().getService(EventListenerRegistry.class);
		//register a post commit listener
		eventListenerRegistry.appendListeners(
				EventType.POST_COMMIT_INSERT,
				new PostInsertEventListener() {
					@Override
					public void onPostInsert(PostInsertEvent event) {
						((IrrelevantEntity) event.getEntity()).setName( postCommitMessage );
					}

					@Override
					public boolean requiresPostCommitHanding(EntityPersister persister) {
						return true;
					}
				}
		);
		
		session.getTransaction().begin();
		
		IrrelevantEntity irrelevantEntityMainSession = new IrrelevantEntity();
		irrelevantEntityMainSession.setName( "main session" );
		session.save( irrelevantEntityMainSession );
		
		//open secondary session to also insert an entity
		Session secondSession = session.sessionWithOptions()
				.connection()
				.flushBeforeCompletion( true )
				.autoClose( true )
				.openSession();

		IrrelevantEntity irrelevantEntitySecondarySession = new IrrelevantEntity();
		irrelevantEntitySecondarySession.setName( "secondary session" );
		secondSession.save( irrelevantEntitySecondarySession );

		session.getTransaction().commit();
		
		//both entities should have their names updated to the postCommitMessage value
		assertEquals(postCommitMessage, irrelevantEntityMainSession.getName());
		assertEquals(postCommitMessage, irrelevantEntitySecondarySession.getName());
	}
