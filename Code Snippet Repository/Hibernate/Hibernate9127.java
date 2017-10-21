	@Test
	public void testMultiSelectAliasToEntityMapUsingAliasesWithFetchJoinList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.preferredCourse", "pc", Criteria.LEFT_JOIN  )
						.setFetchMode( "enrolments", FetchMode.JOIN )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		HqlExecutor hqlAliasToEntityMapExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select s as s, pc as pc from Student s left join s.preferredCourse pc left join fetch s.enrolments order by s.studentNumber" )
						.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Map yogiMap = ( Map ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiMap.get( "s" ) );
				assertEquals(
						yogiExpected.getPreferredCourse().getCourseCode(),
						( ( Course ) yogiMap.get( "pc" ) ).getCourseCode()
				);
				Map shermanMap = ( Map ) resultList.get( 1 );
				assertEquals( shermanExpected, shermanMap.get( "s" ) );
				assertNull( shermanMap.get( "pc" ) );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertEquals( yogiExpected.getPreferredCourse(), yogiMap.get( "pc" ) );
					assertTrue( Hibernate.isInitialized( ( ( Student ) yogiMap.get( "s" ) ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) yogiMap.get( "s" ) ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) shermanMap.get( "s" ) ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) shermanMap.get( "s" ) ).getEnrolments() ) );
				}
			}
		};
		runTest( hqlAliasToEntityMapExecutor, null, checker, false );
	}
