	@Test
	public void cannotCommitTransaction() throws Exception {
		TransactionAttribute txatt = new DefaultTransactionAttribute();

		Method m = setNameMethod;
		MapTransactionAttributeSource tas = new MapTransactionAttributeSource();
		tas.register(m, txatt);
		// Method m2 = getNameMethod;
		// No attributes for m2

		PlatformTransactionManager ptm = mock(PlatformTransactionManager.class);

		TransactionStatus status = mock(TransactionStatus.class);
		given(ptm.getTransaction(txatt)).willReturn(status);
		UnexpectedRollbackException ex = new UnexpectedRollbackException("foobar", null);
		willThrow(ex).given(ptm).commit(status);

		TestBean tb = new TestBean();
		ITestBean itb = (ITestBean) advised(tb, ptm, tas);

		String name = "new name";
		try {
			itb.setName(name);
			fail("Shouldn't have succeeded");
		}
		catch (UnexpectedRollbackException thrown) {
			assertTrue(thrown == ex);
		}

		// Should have invoked target and changed name
		assertTrue(itb.getName() == name);
	}
