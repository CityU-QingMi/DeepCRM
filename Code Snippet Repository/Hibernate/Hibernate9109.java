	@Test
	public void testMultiEntityProjectionList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use PassThroughTransformer by default
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection(
								Projections.projectionList()
										.add( Property.forName( "e.student" ) )
										.add( Property.forName( "e.semester" ) )
										.add( Property.forName( "e.year" ) )
										.add( Property.forName( "e.course" ) )
						)
						.addOrder( Order.asc( "e.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.student, e.semester, e.year, e.course from Enrolment e order by e.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( 4, yogiObjects.length );
				// TODO: following is initialized for hql and uninitialied for criteria; why?
				//assertFalse( Hibernate.isInitialized( yogiObjects[ 0 ] ) );
				//assertFalse( Hibernate.isInitialized( shermanObjects[ 0 ] ) );
				assertTrue( yogiObjects[ 0 ] instanceof Student );
				assertTrue( shermanObjects[ 0 ] instanceof Student );
				assertEquals( yogiEnrolmentExpected.getSemester(), ( (Short) yogiObjects[ 1 ] ).shortValue() );
				assertEquals( yogiEnrolmentExpected.getYear(), ( (Short) yogiObjects[ 2 ] ).shortValue() );
				assertEquals( courseExpected, yogiObjects[ 3 ] );
				assertEquals( shermanEnrolmentExpected.getSemester(), ( (Short) shermanObjects[ 1 ] ).shortValue() );
				assertEquals( shermanEnrolmentExpected.getYear(), ( (Short) shermanObjects[ 2 ] ).shortValue() );
				assertTrue( shermanObjects[ 3 ] instanceof Course );
				assertEquals( courseExpected, shermanObjects[ 3 ] );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
