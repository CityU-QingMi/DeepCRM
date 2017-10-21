	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintReadPastLocking() {
		final String expectedLockHint = "tab1 with (updlock, rowlock, readpast)";

		LockOptions lockOptions = new LockOptions( LockMode.UPGRADE_SKIPLOCKED );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
