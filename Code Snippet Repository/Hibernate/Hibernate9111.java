	@Test
	public void testSingleAggregatedPropProjectionSingleResult() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class )
						.setProjection( Projections.min( "studentNumber" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select min( e.studentNumber ) from Enrolment e" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Long );
				assertEquals( Long.valueOf( yogiExpected.getStudentNumber() ), results );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
