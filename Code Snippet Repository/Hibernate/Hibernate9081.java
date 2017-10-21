	@Test
	public void testEntityWithUnaliasedJoinFetchedLazyOneToManySingleElementList() throws Exception {
		// unaliased
		CriteriaExecutor criteriaExecutorUnaliased = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.setFetchMode( "enrolments", FetchMode.JOIN )
						.addOrder( Order.asc( "s.studentNumber" ) );
			}
		};
		HqlExecutor hqlExecutorUnaliased = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "from Student s left join fetch s.enrolments order by s.studentNumber" );
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

		runTest( hqlExecutorUnaliased, criteriaExecutorUnaliased, checker, false);
	}
