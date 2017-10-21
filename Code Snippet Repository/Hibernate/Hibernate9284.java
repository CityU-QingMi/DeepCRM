	@Test
	@SuppressWarnings("")
	public void explicitJoinOutsideTxnTest() throws Exception {
		// pre conditions
		final TransactionManager tm = JtaPlatformStandardTestingImpl.INSTANCE.transactionManager();
		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );

		final JtaTransactionCoordinatorImpl transactionCoordinator = buildTransactionCoordinator( false );

		assertEquals( Status.STATUS_NO_TRANSACTION, tm.getStatus() );

		// try to force a join, should fail...
		try {
			transactionCoordinator.explicitJoin();
			fail( "Expecting explicitJoin() call outside of transaction to fail" );
		}
		catch (TransactionRequiredForJoinException expected) {
		}
	}
