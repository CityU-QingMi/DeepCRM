	@Test
	public void transactionShouldSucceed() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(getNameMethod, txatt);

		TransactionStatus status = mock(TransactionStatus.class);
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);
		// expect a transaction
		given(ptm.getTransaction(txatt)).willReturn(status);

		TestBean tb = new TestBean();
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		checkTransactionStatus(false);
		itb.getName();
		checkTransactionStatus(false);

		verify(ptm).commit(status);
	}
