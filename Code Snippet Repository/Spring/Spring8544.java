	@Test
	public void rollbackTxButDoNotStartNewTx() {
		assertInTransaction(true);
		assertTrue(TestTransaction.isActive());
		assertUsers("Dilbert");
		deleteFromTables("user");
		assertUsers();

		// Rollback (automatically)
		assertTrue(TestTransaction.isFlaggedForRollback());
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		assertInTransaction(false);
		assertUsers("Dilbert");
	}
