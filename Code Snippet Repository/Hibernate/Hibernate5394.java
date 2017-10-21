	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintUpgrade() {
		final String expectedLockHint = "tab1 with (updlock, holdlock, rowlock)";

		LockOptions lockOptions = new LockOptions( LockMode.UPGRADE );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
