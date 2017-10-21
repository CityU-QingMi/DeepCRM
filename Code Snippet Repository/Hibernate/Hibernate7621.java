	@Test
	public void testPessimisticLockWithCountDistinctThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products = session.createQuery(
			"select p from Product p where ( select count(distinct p1.id) from Product p1 ) > 0 ", Product.class )
		.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ).setFollowOnLocking( false ) )
		.getResultList();

		assertEquals( 50, products.size() );
		assertEquals( 1, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
