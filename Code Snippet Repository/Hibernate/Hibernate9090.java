	@Test
	public void testEntityWithJoinFetchedLazyManyToOneUsingProjectionList() throws Exception {
		// unaliased
		CriteriaExecutor criteriaExecutor = new CriteriaExecutor() {
			protected Criteria getCriteria(Session s) {
				// should use RootEntityTransformer by default
				return s.createCriteria( Enrolment.class, "e" )
						.createAlias( "e.student", "s", Criteria.LEFT_JOIN )
						.setFetchMode( "student", FetchMode.JOIN )
						.setFetchMode( "student.preferredCourse", FetchMode.JOIN )
						.setProjection(
								Projections.projectionList()
										.add( Projections.property( "s.name" ) )
										.add( Projections.property( "e.student" ) )
						)
						.addOrder( Order.asc( "s.studentNumber") );
			}
		};
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery(
						"select s.name, s from Enrolment e left join e.student s left join fetch s.preferredCourse order by s.studentNumber"
				);
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( yogiExpected.getName(), yogiObjects[ 0 ] );
				assertEquals( shermanExpected.getName(), shermanObjects[ 0 ] );
				// The following fails for criteria due to HHH-1425
				// assertEquals( yogiExpected, yogiObjects[ 1 ] );
				// assertEquals( shermanExpected, shermanObjects[ 1 ] );
				assertEquals( yogiExpected.getStudentNumber(), ( ( Student ) yogiObjects[ 1 ] ).getStudentNumber() );
				assertEquals( shermanExpected.getStudentNumber(), ( ( Student ) shermanObjects[ 1 ] ).getStudentNumber() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					// The following fails for criteria due to HHH-1425
					//assertTrue( Hibernate.isInitialized( ( ( Student ) yogiObjects[ 1 ] ).getPreferredCourse() ) );
					//assertEquals( yogiExpected.getPreferredCourse(),  ( ( Student ) yogiObjects[ 1 ] ).getPreferredCourse() );
					//assertTrue( Hibernate.isInitialized( ( ( Student ) shermanObjects[ 1 ] ).getPreferredCourse() ) );
					//assertEquals( shermanExpected.getPreferredCourse(),  ( ( Student ) shermanObjects[ 1 ] ).getPreferredCourse() );
				}
			}
		};
		runTest( hqlExecutor, criteriaExecutor, checker, false );
	}
