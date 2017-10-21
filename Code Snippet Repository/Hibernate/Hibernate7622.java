	@Test
	public void testPessimisticLockWithFirstResultsWhileExplicitlyDisablingFollowOnLockingThenFails() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		try {
			List<Product> products =
					session.createQuery(
							"select p from Product p", Product.class )
							.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
													 .setFollowOnLocking( false ) )
							.setFirstResult( 40 )
							.setMaxResults( 10 )
							.getResultList();
			fail( "Should throw exception since Oracle does not support ORDER BY if follow on locking is disabled" );
		}
		catch ( PersistenceException expected ) {
			assertEquals(
					SQLGrammarException.class,
					expected.getCause().getClass()
			);
		}
	}
