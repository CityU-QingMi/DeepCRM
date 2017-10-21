	protected void runTest(QueryExecutor queryExecutor, ResultChecker resultChecker, boolean isSingleResult) throws Exception{
		clearCache();
		clearStatistics();

		Object results = queryExecutor.execute( isSingleResult );

		assertHitCount( 0 );
		assertMissCount( isQueryCacheGetEnabled() ? 1 : 0 );
		assertPutCount( isQueryCachePutEnabled() ? 1 : 0 );
		clearStatistics();

		resultChecker.check( results );

		// check again to make sure nothing got initialized while checking results;
		assertHitCount( 0 );
		assertMissCount( 0 );
		assertPutCount( 0 );
		clearStatistics();

		results = queryExecutor.execute( isSingleResult );

		assertHitCount( isQueryCacheGetEnabled() ? 1 : 0 );
		assertMissCount( 0 );
		assertPutCount( ! isQueryCacheGetEnabled() && isQueryCachePutEnabled() ? 1 : 0 );
		clearStatistics();

		resultChecker.check( results );

		// check again to make sure nothing got initialized while checking results;
		assertHitCount( 0 );
		assertMissCount( 0 );
		assertPutCount( 0 );
		clearStatistics();
	}
