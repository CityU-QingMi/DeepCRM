	private void doTestGetsAreNotTransactional(final ITestBean testBean) {
		// Install facade
		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);
		PlatformTransactionManagerFacade.delegate = ptm;

		assertTrue("Age should not be " + testBean.getAge(), testBean.getAge() == 666);

		// Expect no methods
		verifyZeroInteractions(ptm);

		// Install facade expecting a call
		final TransactionStatus ts = mock(TransactionStatus.class);
		ptm = new PlatformTransactionManager() {
			private boolean invoked;
			@Override
			public TransactionStatus getTransaction(@Nullable TransactionDefinition def) throws TransactionException {
				if (invoked) {
					throw new IllegalStateException("getTransaction should not get invoked more than once");
				}
				invoked = true;
				if (!(def.getName().contains(DerivedTestBean.class.getName()) && def.getName().contains("setAge"))) {
					throw new IllegalStateException(
							"transaction name should contain class and method name: " + def.getName());
				}
				return ts;
			}
			@Override
			public void commit(TransactionStatus status) throws TransactionException {
				assertTrue(status == ts);
			}
			@Override
			public void rollback(TransactionStatus status) throws TransactionException {
				throw new IllegalStateException("rollback should not get invoked");
			}
		};
		PlatformTransactionManagerFacade.delegate = ptm;

		// TODO same as old age to avoid ordering effect for now
		int age = 666;
		testBean.setAge(age);
		assertTrue(testBean.getAge() == age);
	}
