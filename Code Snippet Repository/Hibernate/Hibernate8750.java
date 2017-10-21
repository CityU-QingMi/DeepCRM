	@Test
	@TestForIssue(jiraKey = "")
	public void testQueryLockModePessimisticWriteWithAlias() {
		doInHibernate( this::sessionFactory, session -> {
			// shouldn't throw an exception
			session.createQuery( "SELECT MAX(a.id)+1 FROM A a where a.value = :value" )
					.setLockMode( "a", LockMode.PESSIMISTIC_WRITE )
					.setParameter( "value", "it" )
					.list();
		} );
	}
