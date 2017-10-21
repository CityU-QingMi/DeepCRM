	@Test
	public void programmaticRollback() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		Method m = getNameMethod;
		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(m, txatt);

		TransactionStatus status = mock(TransactionStatus.class);
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);

		given(ptm.getTransaction(txatt)).willReturn(status);

		final String name = "jenny";
		TestBean tb = new TestBean() {
			@Override
			public String getName() {
				TransactionStatus txStatus = TransactionInterceptor.currentTransactionStatus();
				txStatus.setRollbackOnly();
				return name;
			}
		};

		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		// verification!?
		assertTrue(name.equals(itb.getName()));

		verify(ptm).commit(status);
	}
