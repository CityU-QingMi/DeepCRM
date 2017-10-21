	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryLockModeNoneWithAlias() {
		doInHibernate( this::sessionFactory, session -> {
			// shouldn't throw an exception
			session.createQuery( "SELECT a.value FROM A a where a.id = :id" )
					.setLockMode( "a", LockMode.NONE )
					.setParameter( "id", 1L )
					.list();
		} );
	}
