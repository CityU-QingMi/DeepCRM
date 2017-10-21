	@Test
	public void noTransaction() throws Exception {
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);

		TestBean tb = new TestBean();
		TransactionAttributeSource tas = new MapTransactionAttributeSource();

		// All the methods in this class use the advised() template method
		// to obtain a transaction object, configured with the given PlatformTransactionManager
		// and transaction attribute source
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		checkTransactionStatus(false);
		itb.getName();
		checkTransactionStatus(false);

		// expect no calls
		verifyZeroInteractions(ptm);
	}
