	@Test
	public void testNativeSQLQueryReturnTypes() {
		NativeSQLQueryScalarReturn r1 = new NativeSQLQueryScalarReturn( "a",sessionFactory().getTypeResolver().basic( "int" ) );
		NativeSQLQueryRootReturn r2 = new NativeSQLQueryRootReturn( "a", "b", LockMode.NONE );
		NativeSQLQueryJoinReturn r3 = new NativeSQLQueryJoinReturn( "a", "b", "c", Collections.singletonMap( "key", "value" ), LockMode.NONE );
		NativeSQLQueryCollectionReturn r4 = new NativeSQLQueryCollectionReturn( "a", "b", "c", Collections.singletonMap( "key", "value" ), LockMode.NONE);

		check( false, r1, r2 );
		check( false, r1, r3 );
		check( false, r1, r4 );

		check( false, r2, r3 );
		check( false, r2, r4 );

		check( false, r3, r4 );
	}
