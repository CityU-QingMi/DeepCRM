	@Test
	public void testMultiSelectNewMapUsingAliasesWithFetchJoinList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.preferredCourse", "pc", Criteria.LEFT_JOIN  )
						.setFetchMode( "enrolments", FetchMode.JOIN )
						.addOrder( Order.asc( "s.studentNumber" ))
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlSelectNewMapExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select new map(s as s, pc as pc) from Student s left join s.preferredCourse pc left join fetch s.enrolments order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiMap.get( "s" ) );
				assertEquals( yogiExpected.getPreferredCourse(), yogiMap.get( "pc" ) );
				Map shermanMap = ( Map ) resultList.get( 1 );
				assertEquals( shermanExpected, shermanMap.get( "s" ) );
				assertNull( shermanMap.get( "pc" ) );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student ) yogiMap.get( "s" ) ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) yogiMap.get( "s" ) ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) shermanMap.get( "s" ) ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) shermanMap.get( "s" ) ).getEnrolments() ) );
				}
			}
		};
		runTest( hqlSelectNewMapExecutor, criteriaExecutor, checker, false );
	}
