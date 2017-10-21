	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintReadPastLockingNoTimeOut() {
		final String expectedLockHint = "tab1 with (updlock, rowlock, readpast, nowait)";

		LockOptions lockOptions = new LockOptions( LockMode.UPGRADE_SKIPLOCKED );
		lockOptions.setTimeOut( LockOptions.NO_WAIT );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
