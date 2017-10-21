	@Test
	public void testTransactionCommitWithRollbackException() {
		given(manager.getTransaction()).willReturn(tx);
		given(tx.getRollbackOnly()).willReturn(true);
		willThrow(new RollbackException()).given(tx).commit();

		final List<String> l = new ArrayList<>();
		l.add("test");

		assertTrue(!TransactionSynchronizationManager.hasResource(factory));
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());

		try {
			Object result = tt.execute(new TransactionCallback() {
				@Override
				public Object doInTransaction(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.hasResource(factory));
					EntityManagerFactoryUtils.getTransactionalEntityManager(factory).flush();
					return l;
				}
			});
			assertSame(l, result);
		}
		catch (TransactionSystemException tse) {
			// expected
			assertTrue(tse.getCause() instanceof RollbackException);
		}

		assertTrue(!TransactionSynchronizationManager.hasResource(factory));
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());

		verify(manager).flush();
		verify(manager).close();
	}
