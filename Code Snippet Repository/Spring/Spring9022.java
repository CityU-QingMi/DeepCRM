	@Test
	public void jtaTransactionManagerWithSystemExceptionOnIsExisting() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willThrow(new SystemException("system exception"));

		try {
			JtaTransactionManager ptm = newJtaTransactionManager(ut);
			TransactionTemplate tt = new TransactionTemplate(ptm);
			tt.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					// something transactional
				}
			});
			fail("Should have thrown TransactionSystemException");
		}
		catch (TransactionSystemException ex) {
			// expected
		}
	}
