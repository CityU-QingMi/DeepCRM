	@Test
	public void testPessimisticLockWithDistinctWhileExplicitlyDisablingFollowOnLockingThenFails() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		try {
			List<Product> products =
					session.createQuery(
							"select distinct p from Product p where p.id > 40",
							Product.class
					)
							.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
													 .setFollowOnLocking( false ) )
							.getResultList();
			fail( "Should throw exception since Oracle does not support DISTINCT if follow on locking is disabled" );
		}
		catch ( PersistenceException expected ) {
			assertEquals(
					SQLGrammarException.class,
					expected.getCause().getClass()
			);
		}
	}
