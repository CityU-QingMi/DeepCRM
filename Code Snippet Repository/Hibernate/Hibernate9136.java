	protected void runTest(HqlExecutor hqlExecutor, CriteriaExecutor criteriaExecutor, ResultChecker checker, boolean isSingleResult)
		throws Exception {
		createData();
		try {
			if ( criteriaExecutor != null ) {
				runTest( criteriaExecutor, checker, isSingleResult );
			}
			if ( hqlExecutor != null ) {
				runTest( hqlExecutor, checker, isSingleResult );
			}
		}
		finally {
			deleteData();
		}
	}
