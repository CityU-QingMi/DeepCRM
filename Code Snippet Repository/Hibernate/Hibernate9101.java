	@Test
	public void testAliasToEntityMapMultiAggregatedPropProjectionSingleResult() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Enrolment.class )
						.setProjection(
								Projections.projectionList()
									.add( Projections.min( "studentNumber" ).as( "minStudentNumber" ) )
									.add( Projections.max( "studentNumber" ).as( "maxStudentNumber" ) )
						)
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery(
						"select min( e.studentNumber ) as minStudentNumber, max( e.studentNumber ) as maxStudentNumber from Enrolment e" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				assertTrue( results instanceof Map );
				Map resultMap = ( Map ) results;
				assertEquals( 2, resultMap.size() );
				assertEquals( yogiExpected.getStudentNumber(), resultMap.get( "minStudentNumber" ) );
				assertEquals( shermanExpected.getStudentNumber(), resultMap.get( "maxStudentNumber" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, true );
	}
