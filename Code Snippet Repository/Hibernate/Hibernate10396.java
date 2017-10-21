	@Test
	@TestForIssue(jiraKey = "")
	public void testTransactionProcessSynchronization() {
		final EventListenerRegistry registry = sessionFactory().getServiceRegistry()
				.getService( EventListenerRegistry.class );
		final CountingPostInsertTransactionBoundaryListener listener = new CountingPostInsertTransactionBoundaryListener();

		registry.getEventListenerGroup( EventType.POST_INSERT ).appendListener( listener );

		Session session = openSession();
		session.getTransaction().begin();
		StrTestEntity entity = new StrTestEntity( "str1" );
		session.save( entity );
		session.getTransaction().commit();
		session.close();

		// Post insert listener invoked three times - before/after insertion of original data,
		// revision entity and audit row.
		Assert.assertEquals( 3, listener.getBeforeCount() );
		Assert.assertEquals( 3, listener.getAfterCount() );
	}
