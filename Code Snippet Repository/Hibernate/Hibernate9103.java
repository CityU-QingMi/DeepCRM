	@Test
	public void testOneNonEntityProjectionUnique() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use PassThroughTransformer by default
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection( Projections.property( "e.semester" ) )
						.add( Restrictions.eq( "e.studentNumber", shermanEnrolmentExpected.getStudentNumber() ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.semester from Enrolment e where e.studentNumber = :studentNumber" )
						.setParameter( "studentNumber", shermanEnrolmentExpected.getStudentNumber() );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Short );
				assertEquals( Short.valueOf( shermanEnrolmentExpected.getSemester() ), results );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
