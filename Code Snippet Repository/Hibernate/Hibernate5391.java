	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintWriteWithNoTimeOut() {
		final String expectedLockHint = "tab1 with (updlock, holdlock, rowlock, nowait)";

		LockOptions lockOptions = new LockOptions( LockMode.WRITE );
		lockOptions.setTimeOut( LockOptions.NO_WAIT );

		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
