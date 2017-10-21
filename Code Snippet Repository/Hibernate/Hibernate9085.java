	@Test
	public void testJoinWithFetchJoinWithPropAndOwnerProjectedList() throws Exception {
		HqlExecutor hqlSelectNewMapExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery( "select s.name, s from Student s left join fetch s.enrolments left join s.preferredCourse order by s.studentNumber" );
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				assertEquals( yogiExpected.getName(), yogiObjects[ 0 ] );
				assertEquals( yogiExpected, yogiObjects[ 1 ] );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( shermanExpected.getName(), shermanObjects[ 0 ] );
				assertEquals( shermanExpected, shermanObjects[1] );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student )  yogiObjects[ 1 ] ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) yogiObjects[ 1 ] ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) shermanObjects[ 1 ] ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) shermanObjects[ 1 ] ).getEnrolments() ) );
				}
			}
		};
		runTest( hqlSelectNewMapExecutor, null, checker, false );
	}
