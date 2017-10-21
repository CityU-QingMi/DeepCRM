	@Test
	public void testPessimisticLockWithGroupByWhileExplicitlyDisablingFollowOnLockingThenFails() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		try {
			List<Object[]> products =
					session.createQuery(
							"select count(p), p " +
									"from Product p " +
									"group by p.id, p.name " )
							.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
													 .setFollowOnLocking( false ) )
							.getResultList();
			fail( "Should throw exception since Oracle does not support GROUP BY if follow on locking is disabled" );
		}
		catch ( PersistenceException expected ) {
			assertEquals(
					SQLGrammarException.class,
					expected.getCause().getClass()
			);
		}
	}
