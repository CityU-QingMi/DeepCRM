	@Test
	public void testEntityWithJoinedLazyManyToOneListHql() throws Exception {
		HqlExecutor hqlExecutorUnaliased = new HqlExecutor() {
			protected Query getQuery(Session s) {
				// should use RootEntityTransformer by default
				return s.createQuery( "from Student s left join s.preferredCourse order by s.studentNumber" );
			}
		};
		HqlExecutor hqlExecutorAliased = new HqlExecutor() {
			protected Query getQuery(Session s) {
				// should use RootEntityTransformer by default
				return s.createQuery( "from Student s left join s.preferredCourse p order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiObjects[ 0 ] );
				assertEquals( yogiExpected.getPreferredCourse(), yogiObjects[ 1 ] );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( shermanExpected, shermanObjects[ 0 ] );
				assertNull( shermanObjects[ 1 ] );
			}
		};
		runTest( hqlExecutorUnaliased, null, checker, false );
		runTest( hqlExecutorAliased, null, checker, false );
	}
