	@Test
	public void transactionExceptionPropagatedWithCallbackPreference() throws Throwable {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(exceptionalMethod, txatt);

		MockCallbackPreferringTransactionManager ptm = new MockCallbackPreferringTransactionManager();

		TestBean tb = new TestBean();
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		checkTransactionStatus(false);
		try {
			itb.exceptional(new OptimisticLockingFailureException(""));
			fail("Should have thrown OptimisticLockingFailureException");
		}
		catch (OptimisticLockingFailureException ex) {
			// expected
		}
		checkTransactionStatus(false);

		assertSame(txatt, ptm.getDefinition());
		assertFalse(ptm.getStatus().isRollbackOnly());
	}
