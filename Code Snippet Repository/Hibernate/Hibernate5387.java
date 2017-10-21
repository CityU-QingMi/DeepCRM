	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintPessimisticRead() {
		final String expectedLockHint = "tab1 with (holdlock, rowlock)";

		LockOptions lockOptions = new LockOptions( LockMode.PESSIMISTIC_READ );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
