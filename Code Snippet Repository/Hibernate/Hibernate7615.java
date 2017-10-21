	@Test
	public void testPessimisticLockWithGroupByWhileExplicitlyEnablingFollowOnLockingThenFollowOnLocking() {

		final Session session = openSession();
		session.beginTransaction();

		sqlStatementInterceptor.getSqlQueries().clear();

		List<Object[]> products =
				session.createQuery(
						"select count(p), p " +
								"from Product p " +
								"group by p.id, p.name " )
						.setLockOptions( new LockOptions( LockMode.PESSIMISTIC_WRITE )
												 .setFollowOnLocking( true ) )
						.getResultList();

		assertEquals( 50, products.size() );
		assertEquals( 51, sqlStatementInterceptor.getSqlQueries().size() );

		session.getTransaction().commit();
		session.close();
	}
