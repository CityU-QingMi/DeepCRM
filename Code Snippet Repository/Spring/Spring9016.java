	@Test
	public void jtaTransactionManagerWithPropagationRequiresNewAndExisting() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		TransactionManager tm = mock(TransactionManager.class);
		Transaction tx = mock(Transaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);
		given(tm.suspend()).willReturn(tx);

		JtaTransactionManager ptm = newJtaTransactionManager(ut, tm);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
			}
		});
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());

		verify(ut).begin();
		verify(ut).commit();
		verify(tm).resume(tx);
	}
