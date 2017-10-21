	@Test
	public void jtaTransactionManagerWithExistingTransactionAndRollbackOnlyAndNoGlobalRollback() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);

		final TransactionSynchronization synch = mock(TransactionSynchronization.class);

		JtaTransactionManager ptm = newJtaTransactionManager(ut);
		ptm.setGlobalRollbackOnParticipationFailure(false);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				TransactionSynchronizationManager.registerSynchronization(synch);
				status.setRollbackOnly();
			}
		});
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());

		verify(ut).setRollbackOnly();
		verify(synch).beforeCompletion();
		verify(synch).afterCompletion(TransactionSynchronization.STATUS_UNKNOWN);
	}
