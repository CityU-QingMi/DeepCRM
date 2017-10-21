	@Test
	@TestForIssue(jiraKey = "")
	public void testAppendLockHintPessimisticReadNoTimeOut() {
		final String expectedLockHint = "tab1 with (holdlock, rowlock, nowait)";

		LockOptions lockOptions = new LockOptions( LockMode.PESSIMISTIC_READ );
		lockOptions.setTimeOut( LockOptions.NO_WAIT );
		String lockHint = dialect.appendLockHint( lockOptions, "tab1" );

		assertEquals( expectedLockHint, lockHint );
	}
