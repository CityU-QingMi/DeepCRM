	@Test
	public void testMultiAggregatedPropProjectionSingleResult() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class )
						.setProjection(
								Projections.projectionList()
									.add( Projections.min( "studentNumber" ).as( "minStudentNumber" ) )
									.add( Projections.max( "studentNumber" ).as( "maxStudentNumber" ) )
						);
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery(
						"select min( e.studentNumber ) as minStudentNumber, max( e.studentNumber ) as maxStudentNumber from Enrolment e" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Object[] );
				Object[] resultObjects = ( Object[] ) results;
				assertEquals( Long.valueOf( yogiExpected.getStudentNumber() ), resultObjects[ 0 ] );
				assertEquals( Long.valueOf( shermanExpected.getStudentNumber() ), resultObjects[ 1 ] );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
