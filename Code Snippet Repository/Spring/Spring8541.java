	@Test
	public void commitTxAndStartNewTx() {
		assertInTransaction(true);
		assertTrue(TestTransaction.isActive());
		assertUsers("Dilbert");
		deleteFromTables("user");
		assertUsers();

		// Commit
		TestTransaction.flagForCommit();
		assertFalse(TestTransaction.isFlaggedForRollback());
		TestTransaction.end();
		assertInTransaction(false);
		assertFalse(TestTransaction.isActive());
		assertUsers();

		executeSqlScript("classpath:/org/springframework/test/context/jdbc/data-add-dogbert.sql", false);
		assertUsers("Dogbert");

		TestTransaction.start();
		assertInTransaction(true);
		assertTrue(TestTransaction.isActive());
	}
