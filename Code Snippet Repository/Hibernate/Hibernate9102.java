	public void testAliasToEntityMapNoProjectionList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			@Override
            protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.enrolments", "e", CriteriaSpecification.LEFT_JOIN )
						.createAlias( "e.course", "c", CriteriaSpecification.LEFT_JOIN )
								.setResultTransformer( CriteriaSpecification.ALIAS_TO_ENTITY_MAP )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			@Override
            public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join s.enrolments e left join e.course c order by s.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				assertEquals( 3, yogiMap.size() );
				Map shermanMap = ( Map ) resultList.get( 1 );
				assertEquals( 3, shermanMap.size() );
				assertEquals( yogiExpected, yogiMap.get( "s" ) );
				assertEquals( yogiEnrolmentExpected, yogiMap.get( "e" ) );
				assertEquals( courseExpected, yogiMap.get( "c" ) );
				assertEquals( shermanExpected, shermanMap.get( "s" ) );
				assertEquals( shermanEnrolmentExpected, shermanMap.get( "e" ) );
				assertEquals( courseExpected, shermanMap.get( "c" ) );
				assertSame( ( ( Map ) resultList.get( 0 ) ).get( "c" ), shermanMap.get( "c" ) );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
