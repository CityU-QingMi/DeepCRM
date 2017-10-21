	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintWrite() {
		final String expectedLockHint = "tab1 with (updlock, holdlock, rowlock)";

		LockOptions lockOptions = new LockOptions( LockMode.WRITE );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
