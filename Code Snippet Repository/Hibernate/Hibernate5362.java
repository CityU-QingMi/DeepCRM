	@Test
	@TestForIssue(jiraKey = "")
	public void testTemporaryTableNameTruncation() throws Exception {
		final AbstractMultiTableBulkIdStrategyImpl strategy = (AbstractMultiTableBulkIdStrategyImpl) new Oracle8iDialect().getDefaultMultiTableBulkIdStrategy();

		String temporaryTableName = strategy.getIdTableSupport().generateIdTableName(
				"TABLE_NAME_THAT_EXCEEDS_30_CHARACTERS"
		);

		assertEquals(
				"Temporary table names should be truncated to 30 characters",
				30,
				temporaryTableName.length()
		);
		assertEquals(
				"Temporary table names should start with HT_",
				"HT_TABLE_NAME_THAT_EXCEEDS_30_",
				temporaryTableName
		);
	}
