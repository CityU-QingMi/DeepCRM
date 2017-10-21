	@Test
	public void testJdbcCoordinatorTransactionTimeoutCheck() {
		Session session = openSession();
		Transaction transaction = session.getTransaction();
		transaction.setTimeout( 2 );
		assertEquals( -1, ((SessionImplementor)session).getJdbcCoordinator().determineRemainingTransactionTimeOutPeriod() );
		transaction.begin();
		assertNotSame( -1, ((SessionImplementor)session).getJdbcCoordinator().determineRemainingTransactionTimeOutPeriod() );
		transaction.commit();
		session.close();
	}
