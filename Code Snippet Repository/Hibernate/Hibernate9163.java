	@Test
	public void testNativeSQLQueryRootReturn() {
		NativeSQLQueryRootReturn alias = new NativeSQLQueryRootReturn( "abc", null, null);
		NativeSQLQueryRootReturn diffAlias = new NativeSQLQueryRootReturn( "def", null, null);
		NativeSQLQueryRootReturn aliasEntityName = new NativeSQLQueryRootReturn( "abc", "Person", null);
		NativeSQLQueryRootReturn aliasDiffEntityName = new NativeSQLQueryRootReturn( "abc", "Customer", null);
		NativeSQLQueryRootReturn aliasEntityNameLockMode = new NativeSQLQueryRootReturn( "abc", "Person", LockMode.NONE );
		NativeSQLQueryRootReturn aliasEntityNameDiffLockMode = new NativeSQLQueryRootReturn( "abc", "Person", LockMode.OPTIMISTIC );

		check( false, alias, diffAlias );
		check( false, alias, aliasEntityName );
		check( false, alias, aliasDiffEntityName );
		check( false, alias, aliasEntityNameLockMode );
		check( false, alias, aliasEntityNameDiffLockMode );

		check( false, diffAlias, aliasEntityName );
		check( false, diffAlias, aliasDiffEntityName );
		check( false, diffAlias, aliasEntityNameLockMode );
		check( false, diffAlias, aliasEntityNameDiffLockMode );

		check( false, aliasEntityName, aliasDiffEntityName );
		check( false, aliasEntityName, aliasEntityNameLockMode );
		check( false, aliasEntityName, aliasEntityNameDiffLockMode );

		check( false, aliasDiffEntityName, aliasEntityNameLockMode );
		check( false, aliasDiffEntityName, aliasEntityNameDiffLockMode );

		check( false, aliasEntityNameLockMode, aliasEntityNameDiffLockMode );

		check( true, alias, new NativeSQLQueryRootReturn( "abc", null, null) );
		check( true, diffAlias, new NativeSQLQueryRootReturn( "def", null, null) );
		check( true, aliasEntityName, new NativeSQLQueryRootReturn( "abc", "Person", null) );
		check( true, aliasDiffEntityName, new NativeSQLQueryRootReturn( "abc", "Customer", null) );
		check( true, aliasEntityNameLockMode, new NativeSQLQueryRootReturn( "abc", "Person", LockMode.NONE ) );
		check( true, aliasEntityNameDiffLockMode, new NativeSQLQueryRootReturn( "abc", "Person", LockMode.OPTIMISTIC ) );
	}
