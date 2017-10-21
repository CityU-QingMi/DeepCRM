	@Test
	public void testOneNonEntityProjectionList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use PassThroughTransformer by default
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection( Projections.property( "e.semester" ) )
						.addOrder( Order.asc( "e.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.semester from Enrolment e order by e.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiEnrolmentExpected.getSemester(), resultList.get( 0 ) );
				assertEquals( shermanEnrolmentExpected.getSemester(), resultList.get( 1 ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
