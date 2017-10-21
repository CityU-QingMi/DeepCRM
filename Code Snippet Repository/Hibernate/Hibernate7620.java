	@Test
	public void testPessimisticLockWithNamedQueryExplicitlyEnablingFollowOnLockingThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Product> products = session.createNamedQuery(
			"product_by_name", Product.class )
		.getResultList();

		assertEquals( 50, products.size() );
		assertEquals( 51, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
