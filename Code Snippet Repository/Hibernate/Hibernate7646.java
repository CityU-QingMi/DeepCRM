	@Test
	public void testLockTimeoutNoAliasSkipLocked() {
		assertEquals(
				" for update",
				dialect.getForUpdateString( new LockOptions( LockMode.PESSIMISTIC_READ )
													.setTimeOut( LockOptions.SKIP_LOCKED ) )
		);
		assertEquals(
				" for update",
				dialect.getForUpdateString( new LockOptions( LockMode.PESSIMISTIC_WRITE )
													.setTimeOut( LockOptions.SKIP_LOCKED ) )
		);
	}
