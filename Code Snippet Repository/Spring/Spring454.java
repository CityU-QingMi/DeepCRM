	protected void testRollback(TransactionOperationCallback toc, boolean rollback) throws Throwable {
		txManager.clear();
		assertEquals(0, txManager.begun);
		try {
			toc.performTransactionalOperation();
		}
		finally {
			assertEquals(1, txManager.begun);
			assertEquals(rollback ? 0 : 1, txManager.commits);
			assertEquals(rollback ? 1 : 0, txManager.rollbacks);
		}
	}
