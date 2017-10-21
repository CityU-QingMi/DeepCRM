	@Test
	public void testListElementsProjectionList() throws Exception {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select elements(s.secretCodes) from Student s" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 3, resultList.size() );
				assertTrue( resultList.contains( yogiExpected.getSecretCodes().get( 0 ) ) );
				assertTrue( resultList.contains( shermanExpected.getSecretCodes().get( 0 ) ) );
				assertTrue( resultList.contains( shermanExpected.getSecretCodes().get( 1 ) ) );
			}
		};
		runTest( hqlExecutor, null, checker, false );
	}
