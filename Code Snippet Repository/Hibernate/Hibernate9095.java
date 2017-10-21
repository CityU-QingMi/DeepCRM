	@Test
	public void testEntityWithJoinedLazyOneToManySingleElementListHql() throws Exception {
		HqlExecutor hqlExecutorUnaliased = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.enrolments order by s.studentNumber" );
			}
		};
		HqlExecutor hqlExecutorAliased = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.enrolments e order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertTrue( resultList.get( 0 ) instanceof Object[] );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiObjects[ 0 ] );
				assertEquals( yogiEnrolmentExpected, yogiObjects[ 1 ] );
				assertTrue( resultList.get( 0 ) instanceof Object[] );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( shermanExpected, shermanObjects[ 0 ] );
				assertEquals( shermanEnrolmentExpected, shermanObjects[ 1 ] );
			}
		};
		runTest( hqlExecutorUnaliased, null, checker, false );
		runTest( hqlExecutorAliased, null, checker, false );
	}
