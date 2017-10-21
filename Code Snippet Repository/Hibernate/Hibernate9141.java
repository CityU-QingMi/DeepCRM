	@Test
	public void testEntityWithLazyAssnList() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class )
						.addOrder( Order.asc( "studentNumber" ) );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student order by studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertNotNull( ((Student) resultList.get( 0 )).getEnrolments() );
				assertNotNull( ( ( Student ) resultList.get( 0 ) ).getPreferredCourse() );
				assertNotNull( ( ( Student ) resultList.get( 1 ) ).getEnrolments() );
				assertNull( ( ( Student ) resultList.get( 1 ) ).getPreferredCourse() );
				assertFalse( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getEnrolments() ) );
				assertFalse( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getPreferredCourse() ) );
				assertFalse( Hibernate.isInitialized( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
				assertNull( ( ( Student ) resultList.get( 1 ) ).getPreferredCourse() );
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
