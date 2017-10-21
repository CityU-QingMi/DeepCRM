	@Test
	public void testJoinWithFetchJoinListHql() throws Exception {
		HqlExecutor hqlExecutor = new HqlExecutor() {
			public Query getQuery(Session s) {
				return s.createQuery(
						"from Student s left join fetch s.enrolments left join s.preferredCourse order by s.studentNumber"
				);
			}
		};
		ResultChecker checker = new ResultChecker() {
			public void check(Object results) {
				List resultList = ( List ) results;
				assertEquals( 2, resultList.size() );
				Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
				assertEquals( yogiExpected, yogiObjects[ 0 ] );
				assertEquals( yogiExpected.getPreferredCourse(), yogiObjects[ 1 ] );
				Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
				assertEquals( shermanExpected, shermanObjects[ 0 ] );
				assertNull( shermanObjects[1] );
				assertNull( ((Student) shermanObjects[0]).getPreferredCourse() );
				if ( areDynamicNonLazyAssociationsChecked() ) {
					assertTrue( Hibernate.isInitialized( ( ( Student )  yogiObjects[ 0 ] ).getEnrolments() ) );
					assertEquals( yogiExpected.getEnrolments(), ( ( Student ) yogiObjects[ 0 ] ).getEnrolments() );
					assertTrue( Hibernate.isInitialized( ( ( Student ) shermanObjects[ 0 ] ).getEnrolments() ) );
					assertEquals( shermanExpected.getEnrolments(), ( ( ( Student ) shermanObjects[ 0 ] ).getEnrolments() ) );
				}
			}
		};
		runTest( hqlExecutor, null, checker, false );
	}
