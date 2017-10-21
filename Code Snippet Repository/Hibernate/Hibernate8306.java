	@Test
	@TestForIssue(jiraKey = "")
	public void testLoadMultipleHoldersWithDifferentSubtypes() {
		createTestData();
		doInHibernate( this::sessionFactory, session -> {
			Holder task1 = session.find( Holder.class, 1L );
			Holder task2 = session.find( Holder.class, 2L );
			assertNotNull( task1 );
			assertNotNull( task2 );
		} );
	}
