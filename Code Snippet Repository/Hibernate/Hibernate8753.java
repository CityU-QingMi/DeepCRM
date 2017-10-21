	public void testNoWait()
			throws NoSuchFieldException, IllegalAccessException {

		Session session = sessionFactory().openSession();
		session.beginTransaction();
		try {
			session.createQuery(
				"select a from A a", A.class )
			.unwrap( org.hibernate.query.Query.class )
			.setLockOptions(
				new LockOptions( LockMode.PESSIMISTIC_WRITE )
		 	.setTimeOut( LockOptions.NO_WAIT ) )
			.list();

			String lockingQuery = sqlStatementInterceptor.getSqlQueries().getLast();
			assertTrue( lockingQuery.toLowerCase().contains( "nowait") );
		}
		finally {
			session.getTransaction().commit();
			session.close();
		}
	}
