	@Test
	public void twoTransactionsShouldSucceed() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		MapTransactionAttributeSource tas1 = new MapTransactionAttributeSource();
		tas1.register(getNameMethod, txatt);
		MapTransactionAttributeSource tas2 = new MapTransactionAttributeSource();
		tas2.register(setNameMethod, txatt);

		TransactionStatus status = mock(TransactionStatus.class);
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);
		// expect a transaction
		given(ptm.getTransaction(txatt)).willReturn(status);

		TestBean tb = new TestBean();
		ITestBean itb = (ITestBean) advised(tb, ptm, new TransactionAttributeSource[] {tas1, tas2});

		checkTransactionStatus(false);
		itb.getName();
		checkTransactionStatus(false);
		itb.setName("myName");
		checkTransactionStatus(false);

		verify(ptm, times(2)).commit(status);
	}
