	@Test
	public void testOneEntityProjectionList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			// should use PassThroughTransformer by default
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection( Projections.property( "e.student" ) )
						.addOrder( Order.asc( "e.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.student from Enrolment e order by e.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				// TODO: following is initialized for hql and uninitialied for criteria; why?
				//assertFalse( Hibernate.isInitialized( resultList.get( 0 ) ) );
				//assertFalse( Hibernate.isInitialized( resultList.get( 1 ) ) );
				assertEquals( yogiExpected.getStudentNumber(), ( ( Student ) resultList.get( 0 ) ).getStudentNumber() );
				assertEquals( shermanExpected.getStudentNumber(), ( ( Student ) resultList.get( 1 ) ).getStudentNumber() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
