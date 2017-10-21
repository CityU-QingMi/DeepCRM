	@Test
	public void testPessimisticLockWithMaxResultsAndOrderByWhileExplicitlyEnablingFollowOnLockingThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products =
				session.createQuery(
						"select p from Product p order by p.id", Product.class )
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
												 .setFollowOnLocking( true ) )
						.setMaxResults( 10 )
						.getResultList();

		assertEquals( 10, products.size() );
		assertEquals( 11, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
