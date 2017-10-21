	@Test
	public void testMapValueList() throws Exception {
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
				return s.createQuery( "select value(s.addresses) from Student s" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertTrue( resultList.contains( yogiExpected.getAddresses().get( "home" ) ) );
				assertTrue( resultList.contains( yogiExpected.getAddresses().get( "work" ) ) );
			}
		};
		runTest( hqlExecutor, null, checker, false );
	}
