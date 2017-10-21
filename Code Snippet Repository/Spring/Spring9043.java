	@Test
	public void jtaTransactionManagerWithExistingTransactionAndCommitException() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);

		final TransactionSynchronization synch = mock(TransactionSynchronization.class);
		willThrow(new OptimisticLockingFailureException("")).given(synch).beforeCommit(false);

		JtaTransactionManager ptm = newJtaTransactionManager(ut);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		try {
			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
					TransactionSynchronizationManager.registerSynchronization(synch);
				}
			});
			fail("Should have thrown OptimisticLockingFailureException");
		}
		catch (OptimisticLockingFailureException ex) {
			// expected
		}
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());

		verify(ut).setRollbackOnly();
		verify(synch).beforeCompletion();
		verify(synch).afterCompletion(TransactionSynchronization.STATUS_UNKNOWN);
	}
