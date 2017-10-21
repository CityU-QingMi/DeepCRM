	@Test
	public void enclosingTransactionWithNonTransactionMethodOnAdvisedInside() throws Throwable {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(exceptionalMethod, txatt);

		TransactionStatus status = mock(TransactionStatus.class);
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);
		// Expect a transaction
		given(ptm.getTransaction(txatt)).willReturn(status);

		final String spouseName = "innerName";

		TestBean outer = new TestBean() {
			@Override
			public void exceptional(Throwable t) throws Throwable {
				TransactionInfo ti = TransactionAspectSupport.currentTransactionInfo();
				assertTrue(ti.hasTransaction());
				assertEquals(spouseName, getSpouse().getName());
			}
		};
		TestBean inner = new TestBean() {
			@Override
			public String getName() {
				// Assert that we're in the inner proxy
				TransactionInfo ti = TransactionAspectSupport.currentTransactionInfo();
				assertFalse(ti.hasTransaction());
				return spouseName;
			}
		};

		ITestBean outerProxy = (ITestBean) advised(outer, ptm, tas);
		ITestBean innerProxy = (ITestBean) advised(inner, ptm, tas);
		outer.setSpouse(innerProxy);

		checkTransactionStatus(false);

		// Will invoke inner.getName, which is non-transactional
		outerProxy.exceptional(null);

		checkTransactionStatus(false);

		verify(ptm).commit(status);
	}
