	@Test
	public void testJoinWithFetchJoinListCriteria() throws Exception {
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				return s.createCriteria( Student.class, "s" )
						.createAlias( "s.preferredCourse", "pc", Criteria.LEFT_JOIN  )
						.setFetchMode( "enrolments", FetchMode.JOIN )
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				assertEquals( yogiExpected, resultList.get( 0 ) );
				// The following fails for criteria due to HHH-3524
				//assertEquals( yogiExpected.getPreferredCourse(), ( ( Student ) resultList.get( 0 ) ).getPreferredCourse() );
				assertEquals( yogiExpected.getPreferredCourse().getCourseCode(),
						( ( Student ) resultList.get( 0 ) ).getPreferredCourse().getCourseCode() );
				assertEquals( shermanExpected, resultList.get( 1 ) );
				assertNull( ( ( Student ) resultList.get( 1 ) ).getPreferredCourse() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 0 ) ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) resultList.get( 0 ) ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) resultList.get( 1 ) ).getEnrolments() ) );
				}
			}
		};
		runTest( null, criteriaExecutor, checker, false );
	}
