	@Test
	public void testPessimisticLockWithUnionWhileExplicitlyDisablingFollowOnLockingThenFails() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		try {
			List<Vehicle> vehicles = session.createQuery( "select v from Vehicle v" )
					.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ).setFollowOnLocking( false ) )
					.getResultList();
			fail( "Should throw exception since Oracle does not support UNION if follow on locking is disabled" );
		}
		catch ( PersistenceException expected ) {
			assertEquals(
					SQLGrammarException.class,
					expected.getCause().getClass()
			);
		}
	}
