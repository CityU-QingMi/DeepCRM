	@Test
	public void testEntityWithJoinedLazyManyToOneListCriteria() throws Exception {
		CriteriaExecutor criteriaExecutorUnaliased = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.preferredCourse", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased1 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.preferredCourse", "p", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased2 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.preferredCourse", "p", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertEquals( yogiExpected.getPreferredCourse().getCourseCode(),
						( ( Student ) resultList.get( 0 ) ).getPreferredCourse().getCourseCode() );
				assertNull( ( ( Student ) resultList.get( 1 ) ).getPreferredCourse() );
			}
		};
		runTest( null, criteriaExecutorUnaliased, checker, false );
		runTest( null, criteriaExecutorAliased1, checker, false );
		runTest( null, criteriaExecutorAliased2, checker, false );
	}
