	@Test
	public void testPessimisticLockWithMaxResultsThenNoFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products =
				session.createQuery(
						"select p from Product p", Product.class )
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE ) )
						.setMaxResults( 10 )
						.getResultList();

		assertEquals( 10, products.size() );
		assertEquals( 1, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
