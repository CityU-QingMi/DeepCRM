	@Test
	public void transactionShouldSucceedWithCallbackPreference() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(getNameMethod, txatt);

		MockCallbackPreferringTransactionManager ptm = new MockCallbackPreferringTransactionManager();

		TestBean tb = new TestBean();
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		checkTransactionStatus(false);
		itb.getName();
		checkTransactionStatus(false);

		assertSame(txatt, ptm.getDefinition());
		assertFalse(ptm.getStatus().isRollbackOnly());
	}
