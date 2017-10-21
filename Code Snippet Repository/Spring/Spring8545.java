	@Test
	@Commit
	public void rollbackTxAndStartNewTxWithDefaultCommitSemantics() {
		assertInTransaction(true);
		assertTrue(TestTransaction.isActive());
		assertUsers("Dilbert");
		deleteFromTables("user");
		assertUsers();

		// Rollback
		TestTransaction.flagForRollback();
		assertTrue(TestTransaction.isFlaggedForRollback());
		TestTransaction.end();
		assertFalse(TestTransaction.isActive());
		assertInTransaction(false);
		assertUsers("Dilbert");

		// Start new transaction with default commit semantics
		TestTransaction.start();
		assertInTransaction(true);
		assertFalse(TestTransaction.isFlaggedForRollback());
		assertTrue(TestTransaction.isActive());

		executeSqlScript("classpath:/org/springframework/test/context/jdbc/data-add-dogbert.sql", false);
		assertUsers("Dilbert", "Dogbert");
	}
