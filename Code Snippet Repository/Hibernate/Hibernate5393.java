	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintUpgradeNoWaitNoTimeout() {
		final String expectedLockHint = "tab1 with (updlock, holdlock, rowlock, nowait)";

		LockOptions lockOptions = new LockOptions( LockMode.UPGRADE_NOWAIT );
		lockOptions.setTimeOut( LockOptions.NO_WAIT );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
