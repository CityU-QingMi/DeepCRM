	@Test
	@RequiresDialect({ Oracle8iDialect.class, PostgreSQL95Dialect.class })
	public void testSkipLocked()
			throws NoSuchFieldException, IllegalAccessException {

		Session session = sessionFactory().openSession();
		session.beginTransaction();
		try {
			session.createQuery(
				"select a from A a", A.class )
			.unwrap( org.hibernate.query.Query.class )
			.setLockOptions(
				new LockOptions( LockMode.PESSIMISTIC_WRITE )
		 	.setTimeOut( LockOptions.SKIP_LOCKED ) )
			.list();

			String lockingQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertTrue( lockingQuery.toLowerCase().contains( "skip locked") );
		}
		finally {
			session.getTransaction().commit();
			session.close();
		}
	}
