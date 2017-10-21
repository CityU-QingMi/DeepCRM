	@Test
	public void jtaTransactionManagerWithPropagationRequiresNewAndExistingWithSuspendException() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		TransactionManager tm = mock(TransactionManager.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);
		willThrow(new SystemException()).given(tm).suspend();

		JtaTransactionManager ptm = newJtaTransactionManager(ut, tm);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		try {
			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				}
			});
			fail("Should have thrown TransactionSystemException");
		}
		catch (TransactionSystemException ex) {
			// expected
		}
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
	}
