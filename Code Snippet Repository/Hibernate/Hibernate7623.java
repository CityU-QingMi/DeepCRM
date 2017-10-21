	@Test
	public void testPessimisticLockWithFirstResultsWhileExplicitlyEnablingFollowOnLockingThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products =
				session.createQuery(
						"select p from Product p", Product.class )
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
												 .setFollowOnLocking( true ) )
						.setFirstResult( 40 )
						.setMaxResults( 10 )
						.getResultList();

		assertEquals( 10, products.size() );
		assertEquals( 11, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
