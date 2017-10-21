	@Test
	public void testLockTimeoutAliasSkipLocked() {
		String alias = "a";
		assertEquals(
				" for update skip locked",
				dialect.getForUpdateString(
						alias,
						new LockOptions( LockMode.PESSIMISTIC_READ ).setAliasSpecificLockMode(
								alias,
								LockMode.PESSIMISTIC_READ
						)
								.setTimeOut( LockOptions.SKIP_LOCKED )
				)
		);
		assertEquals(
				" for update skip locked",
				dialect.getForUpdateString(
						alias,
						new LockOptions( LockMode.PESSIMISTIC_WRITE ).setAliasSpecificLockMode(
								alias,
								LockMode.PESSIMISTIC_WRITE
						)
								.setTimeOut( LockOptions.SKIP_LOCKED )
				)
		);
	}
