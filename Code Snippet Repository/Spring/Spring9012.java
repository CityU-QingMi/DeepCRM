	@Test
	public void jtaTransactionManagerWithPropagationNotSupported() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		TransactionManager tm = mock(TransactionManager.class);
		Transaction tx = mock(Transaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);
		given(tm.suspend()).willReturn(tx);

		JtaTransactionManager ptm = newJtaTransactionManager(ut, tm);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				status.setRollbackOnly();
			}
		});
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());

		verify(tm).resume(tx);
	}
