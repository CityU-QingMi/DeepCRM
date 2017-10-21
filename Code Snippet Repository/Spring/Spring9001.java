	@Test
	public void jtaTransactionManagerWithNotCacheUserTransaction() throws Exception {
		UserTransaction ut = mock(UserTransaction.class);
		given(ut.getStatus()).willReturn(Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE, Status.STATUS_ACTIVE);

		UserTransaction ut2 = mock(UserTransaction.class);
		given(ut2.getStatus()).willReturn(Status.STATUS_NO_TRANSACTION, Status.STATUS_ACTIVE, Status.STATUS_ACTIVE);

		JtaTransactionManager ptm = new JtaTransactionManager();
		ptm.setJndiTemplate(new ExpectedLookupTemplate("java:comp/UserTransaction", ut));
		ptm.setCacheUserTransaction(false);
		ptm.afterPropertiesSet();

		assertEquals(ut, ptm.getUserTransaction());

		TransactionTemplate tt = new TransactionTemplate(ptm);
		assertEquals(JtaTransactionManager.SYNCHRONIZATION_ALWAYS, ptm.getTransactionSynchronization());
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// something transactional
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
			}
		});

		ptm.setJndiTemplate(new ExpectedLookupTemplate("java:comp/UserTransaction", ut2));
		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// something transactional
				assertTrue(TransactionSynchronizationManager.isSynchronizationActive());
				assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());
			}
		});
		assertTrue(!TransactionSynchronizationManager.isSynchronizationActive());
		assertFalse(TransactionSynchronizationManager.isCurrentTransactionReadOnly());

		verify(ut).begin();
		verify(ut).commit();
		verify(ut2).begin();
		verify(ut2).commit();
	}
