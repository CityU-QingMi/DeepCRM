	@Test
	public void testEntityWithJoinedLazyOneToManyMultiAndNullListCriteria() throws Exception {
		CriteriaExecutor criteriaExecutorUnaliased = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.addresses", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased1 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createCriteria( "s.addresses", "a", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		CriteriaExecutor criteriaExecutorAliased2 = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.addresses", "a", Criteria.LEFT_JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 3, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				assertSame( resultList.get( 0 ), resultList.get( 1 ) );
				assertEquals( shermanExpected, resultList.get( 2 ) );
				assertNotNull( ( ( Student ) resultList.get( 0 ) ).getAddresses() );
				assertNotNull( ( ( Student ) resultList.get( 2 ) ).getAddresses() );
				assertNotNull( ( ( Student ) resultList.get( 1 ) ).getAddresses() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getAddresses() ) );
					assertEquals( yogiExpected.getAddresses(), ( ( Student ) resultList.get( 0 ) ).getAddresses() );
					assertTrue( ( ( Student ) resultList.get( 2 ) ).getAddresses().isEmpty() );
				}
			}
		};
		runTest( null, criteriaExecutorUnaliased, checker, false );
		runTest( null, criteriaExecutorAliased1, checker, false );
		runTest( null, criteriaExecutorAliased2, checker, false );
	}
