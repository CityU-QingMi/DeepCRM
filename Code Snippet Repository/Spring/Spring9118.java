	@Test
	public void cannotCreateTransaction() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		Method m = getNameMethod;
		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(m, txatt);

		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);
		// Expect a transaction
		CannotCreateTransactionException ex = new CannotCreateTransactionException("foobar", null);
		given(ptm.getTransaction(txatt)).willThrow(ex);

		TestBean tb = new TestBean() {
			@Override
			public String getName() {
				throw new UnsupportedOperationException(
						"Shouldn't have invoked target method when couldn't create transaction for transactional method");
			}
		};
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		try {
			itb.getName();
			fail("Shouldn't have invoked method");
		}
		catch (CannotCreateTransactionException thrown) {
			assertTrue(thrown == ex);
		}
	}
