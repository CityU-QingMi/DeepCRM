	@Test
	public void testAliasToEntityMapMultiProjectionWithNullAliasList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class, "e" )
						.setProjection(
								Projections.projectionList()
										.add( Property.forName( "e.student" ), "student" )
										.add( Property.forName( "e.semester" ) )
										.add( Property.forName( "e.year" ) )
										.add( Property.forName( "e.course" ), "course" )
						)
						.addOrder( Order.asc( "e.studentNumber") )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select e.student as student, e.semester, e.year, e.course as course from Enrolment e order by e.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				Map shermanMap = ( Map ) resultList.get( 1 );
				// TODO: following are initialized for hql and uninitialied for criteria; why?
				// assertFalse( Hibernate.isInitialized( yogiMap.get( "student" ) ) );
				// assertFalse( Hibernate.isInitialized( shermanMap.get( "student" ) ) );
				assertTrue( yogiMap.get( "student" ) instanceof Student );
				assertEquals( yogiExpected.getStudentNumber(), ( ( Student ) yogiMap.get( "student" ) ).getStudentNumber() );
				assertEquals( shermanExpected.getStudentNumber(), ( ( Student ) shermanMap.get( "student" ) ).getStudentNumber() );
				assertNull( yogiMap.get( "semester" ) );
				assertNull( yogiMap.get( "year" )  );
				assertEquals( courseExpected, yogiMap.get( "course" ) );
				assertNull( shermanMap.get( "semester" ) );
				assertNull( shermanMap.get( "year" )  );
				assertEquals( courseExpected, shermanMap.get( "course" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
