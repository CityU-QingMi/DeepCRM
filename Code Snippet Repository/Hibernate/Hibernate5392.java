	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintUpgradeNoWait() {
		final String expectedLockHint = "tab1 with (updlock, holdlock, rowlock, nowait)";

		LockOptions lockOptions = new LockOptions( LockMode.UPGRADE_NOWAIT );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
