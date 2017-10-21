	@Test
	public void testPessimisticLockWithDistinctWhileExplicitlyEnablingFollowOnLockingThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products =
				session.createQuery(
						"select distinct p from Product p where p.id > 40" )
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
												 .setFollowOnLocking( true ) )
						.setMaxResults( 10 )
						.getResultList();

		assertEquals( 10, products.size() );
		assertEquals( 11, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
