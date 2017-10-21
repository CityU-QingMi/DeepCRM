	@TestForIssue( jiraKey = "" )
	public void testGetForUpdateStringWithAliasesAndLockOptions() {
		PostgreSQL81Dialect dialect = new PostgreSQL81Dialect();
		LockOptions lockOptions = new LockOptions();
		lockOptions.setAliasSpecificLockMode("tableAlias1", LockMode.PESSIMISTIC_WRITE);

		String forUpdateClause = dialect.getForUpdateString("tableAlias1", lockOptions);
		assertTrue("for update of tableAlias1".equals(forUpdateClause));

		lockOptions.setAliasSpecificLockMode("tableAlias2", LockMode.PESSIMISTIC_WRITE);
		forUpdateClause = dialect.getForUpdateString("tableAlias1,tableAlias2", lockOptions);
		assertTrue("for update of tableAlias1,tableAlias2".equals(forUpdateClause));
	}
