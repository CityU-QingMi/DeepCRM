	@Test
	public void testParticipatingTransactionWithDifferentConnectionObtainedFromSynch() throws Exception {
		DataSource ds2 = mock(DataSource.class);
		final Connection con2 = mock(Connection.class);
		given(ds2.getConnection()).willReturn(con2);

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		assertTrue("Synchronization not active", !TransactionSynchronizationManager.isSynchronizationActive());

		final TransactionTemplate tt = new TransactionTemplate(tm);

		final TestTransactionSynchronization synch =
				new TestTransactionSynchronization(ds, TransactionSynchronization.STATUS_COMMITTED) {
					@Override
					protected void doAfterCompletion(int status) {
						super.doAfterCompletion(status);
						Connection con = DataSourceUtils.getConnection(ds2);
						DataSourceUtils.releaseConnection(con, ds2);
					}
				};

		tt.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) throws RuntimeException {
				TransactionSynchronizationManager.registerSynchronization(synch);
			}
		});

		assertTrue("Hasn't thread connection", !TransactionSynchronizationManager.hasResource(ds));
		assertTrue(synch.beforeCommitCalled);
		assertTrue(synch.beforeCompletionCalled);
		assertTrue(synch.afterCommitCalled);
		assertTrue(synch.afterCompletionCalled);
		assertNull(synch.afterCompletionException);
		verify(con).commit();
		verify(con).close();
		verify(con2).close();
	}
