	@Test
	public void testEntityWithJoinedLazyOneToManySingleElementListCriteria() throws Exception {
		CriteriaExecutor criteriaExecutorUnaliased = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.enrolments", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased1 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.enrolments", "e", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased2 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.enrolments", "e", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertNotNull( ( ( Student ) resultList.get( 0 ) ).getEnrolments() );
				assertNotNull( ( ( Student ) resultList.get( 1 ) ).getEnrolments() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) resultList.get( 0 ) ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( Student ) resultList.get( 1 ) ).getEnrolments() );
				}
			}
		};
		runTest( null, criteriaExecutorUnaliased, checker, false );
		runTest( null, criteriaExecutorAliased1, checker, false );
		runTest( null, criteriaExecutorAliased2, checker, false );
	}
