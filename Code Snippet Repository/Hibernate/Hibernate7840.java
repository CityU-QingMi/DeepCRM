	@Test
	@TestForIssue(jiraKey = "")
	public void testManyToManyCollectionWithActiveFilterOnJoin() {
		doInHibernate( this::sessionFactory, session -> {
			session.enableFilter( "activeUserFilter" );
			session.enableFilter( "activeRoleFilter" );

			final User user = session.get( User.class, 1 );
			assertNotNull( user );
			assertTrue( user.getRoles().isEmpty() );
		} );
	}
