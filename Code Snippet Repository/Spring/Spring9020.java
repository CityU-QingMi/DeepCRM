	@Test
	public void jtaTransactionManagerWithPropagationRequiresNewAndSuspensionNotSupported() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_ACTIVE);

		JtaTransactionManager ptm = newJtaTransactionManager(ut);
		TransactionTemplate tt = new TransactionTemplate(ptm);
		tt.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
		try {
			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
				}
			});
			fail("Should have thrown TransactionSuspensionNotSupportedException");
		}
		catch (TransactionSuspensionNotSupportedException ex) {
			// expected
		}
		assertFalse(TransactionSynchronizationManager.isSynchronizationActive());
	}
