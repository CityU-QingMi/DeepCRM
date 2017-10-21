	@Test
	public void testOneEntityProjectionUnique() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use PassThroughTransformer by default
				return s.createCriteria( Enrolment.class )
						.setProjection( Projections.property( "student" ) )
						.add( Restrictions.eq( "studentNumber", Long.valueOf( yogiExpected.getStudentNumber() ) ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.student from Enrolment e where e.studentNumber = :studentNumber" )
						.setParameter( "studentNumber", Long.valueOf( yogiExpected.getStudentNumber() ) );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Student );
				Student student = ( Student ) results;
				// TODO: following is initialized for hql and uninitialied for criteria; why?
				//assertFalse( Hibernate.isInitialized( student ) );
				assertEquals( yogiExpected.getStudentNumber(), student.getStudentNumber() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
