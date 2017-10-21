	@Test
	public void testAliasToEntityMapMultiProjectionList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection(
								Projections.projectionList()
										.add( Property.forName( "e.student" ), "student" )
										.add( Property.forName( "e.semester" ), "semester" )
										.add( Property.forName( "e.year" ), "year" )
										.add( Property.forName( "e.course" ), "course" )
						)
						.addOrder( Order.asc( "studentNumber") )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.student as student, e.semester as semester, e.year as year, e.course as course from Enrolment e order by e.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				Map shermanMap = ( Map ) resultList.get( 1 );
				assertEquals( 4, yogiMap.size() );
				assertEquals( 4, shermanMap.size() );
				assertTrue( yogiMap.get( "student" ) instanceof Student );
				assertTrue( shermanMap.get( "student" ) instanceof Student );
				// TODO: following are initialized for hql and uninitialied for criteria; why?
				// assertFalse( Hibernate.isInitialized( yogiMap.get( "student" ) ) );
				// assertFalse( Hibernate.isInitialized( shermanMap.get( "student" ) ) );
				assertEquals( yogiExpected.getStudentNumber(), ( ( Student ) yogiMap.get( "student" ) ).getStudentNumber() );
				assertEquals( shermanExpected.getStudentNumber(), ( ( Student ) shermanMap.get( "student" ) ).getStudentNumber() );
				assertEquals( yogiEnrolmentExpected.getSemester(), yogiMap.get( "semester" ) );
				assertEquals( yogiEnrolmentExpected.getYear(), yogiMap.get( "year" )  );
				assertEquals( courseExpected, yogiMap.get( "course" ) );
				assertEquals( shermanEnrolmentExpected.getSemester(), shermanMap.get( "semester" ) );
				assertEquals( shermanEnrolmentExpected.getYear(), shermanMap.get( "year" )  );
				assertEquals( courseExpected, shermanMap.get( "course" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
