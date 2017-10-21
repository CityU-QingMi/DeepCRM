	@Test
	public void testEntityWithSelectFetchedLazyOneToManySingleElementListCriteria() throws Exception {
		CriteriaExecutor criteriaExecutorUnaliased = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.setFetchMode( "enrolments", FetchMode.SELECT )
						.addOrder( Order.asc( "s.studentNumber" ) );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertNotNull( ((Student) resultList.get( 0 )).getEnrolments() );
				assertFalse( Hibernate.isInitialized( ((Student) resultList.get( 0 )).getEnrolments() ) );
				assertNotNull( ( ( Student ) resultList.get( 1 ) ).getEnrolments() );
				assertFalse( Hibernate.isInitialized( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
			}
		};

		runTest( null, criteriaExecutorUnaliased, checker, false);
	}
