	@Test
	public void testPessimisticLockWithDistinctThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products =
				session.createQuery(
						"select distinct p from Product p",
						Product.class
				)
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ) )
						.getResultList();

		assertEquals( 50, products.size() );
		assertEquals( 51, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
